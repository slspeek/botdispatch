package fspotcloud.botdispatch.model.command;

import java.io.IOException;
import java.util.List;

import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.api.NullCommand;

public class CommandManager implements Commands {

	private final Provider<PersistenceManager> pmProvider;
	private Integer maxDelete;

	@Inject
	public CommandManager(Provider<PersistenceManager> pmProvider, @Named("maxCommandDelete") Integer maxDelete) {
		this.pmProvider = pmProvider;
		this.maxDelete = maxDelete;
	}

	public void save(Command c) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.makePersistent(c);
		} finally {
			pm.close();
		}
	}

	@Override
	public int getCountUnderAThousend() {
		PersistenceManager pm = pmProvider.get();
		int count = -1;
		try {
			Query query = pm.newQuery("select id from "+ CommandDO.class.getName());
			@SuppressWarnings("unchecked")
			List<CommandDO> rs = (List<CommandDO>) query.execute();
			count =  rs.size();;
		} finally {
			pm.close();
		}

		return count;
	}

	@Override
	public Command createAndSave(Action<?> action,
			AsyncCallback<? extends Result> callback) {
		Command cmd = null;
		try {
			cmd = new CommandDO(action, callback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		save(cmd);
		return cmd;
	}

	@Override
	public Command getAndLockFirstCommand() {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(CommandDO.class);
			query.setOrdering("ctime");
			query.setFilter("locked == false");
			query.setRange(0, 1);
			@SuppressWarnings("unchecked")
			List<CommandDO> cmdList = (List<CommandDO>) query.execute();
			if (cmdList.size() > 0) {
				Command first = cmdList.get(0);
				first.setLocked(true);
				pm.makePersistent(first);
				return first;
			} else {
				return NullCommand.INSTANCE;
			}
		} finally {
			pm.close();
		}
	}

	@Override
	public Command getById(long callbackId) {
		PersistenceManager pm = pmProvider.get();
		Command cmd = null;
		try {
//			cmd = pm.getObjectById(CommandDO.class, callbackId);
//			cmd = pm.detachCopy(cmd);
			Query q = pm.newQuery(CommandDO.class, "id == :callbackId");
			q.setUnique(true);
			
			cmd = (Command) q.execute(callbackId);
			cmd = pm.detachCopy(cmd);
		} finally {
			pm.close();
		}
		return cmd;
	}

	@Override
	public void delete(Command command) {
		Long id = command.getId(); 
		PersistenceManager pm = pmProvider.get();
		CommandDO cmd = pm.getObjectById(CommandDO.class, id);
		try {
			pm.deletePersistent(cmd);
		} finally {
			pm.close();
		}
	}

	@Override
	public void deleteAll() {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(CommandDO.class);
			query.setRange(0, maxDelete);
			query.setOrdering("ctime");
			query.setRange(0,maxDelete);
			@SuppressWarnings("unchecked")
			List<CommandDO> resultList = (List<CommandDO>) query.execute();
			pm.deletePersistentAll(resultList);
		} finally {
			pm.close();
		}
	}
}
