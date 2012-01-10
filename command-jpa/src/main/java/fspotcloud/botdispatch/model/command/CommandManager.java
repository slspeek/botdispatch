package fspotcloud.botdispatch.model.command;

import java.io.IOException;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.api.NullCommand;
import fspotcloud.botdispatch.model.command.jpa.CommandEntity;

public class CommandManager implements Commands {

	private final Provider<EntityManager> entityManagerProvider;
	private Integer maxDelete;

	@Inject
	public CommandManager(Provider<EntityManager> pmProvider,
			@Named("maxCommandDelete") Integer maxDelete) {
		this.entityManagerProvider = pmProvider;
		this.maxDelete = maxDelete;
	}

	public void save(Command c) {
		EntityManager em = entityManagerProvider.get();
		try {
			em.persist(c);
		} finally {
			em.close();
		}
	}

	@Override
	public int getCountUnderAThousend() {
		EntityManager em = entityManagerProvider.get();
		int count = -1;
		try {
			Query query = em.createQuery("select c.id from "
					+ CommandEntity.class.getName() + " AS c");
			@SuppressWarnings("unchecked")
			List<CommandEntity> rs = (List<CommandEntity>) query
					.getResultList();
			count = rs.size();
		} finally {
			em.close();
		}

		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Command createAndSave(Action<?> action,
			AsyncCallback<? extends Result> callback) {
		Command cmd = null;
		cmd = new CommandEntity(action, (AsyncCallback<Result>) callback);
		save(cmd);
		return cmd;
	}

	@Override
	public Command getAndLockFirstCommand() {
		Command returnValue;
		EntityManager pm = entityManagerProvider.get();
		Query query = pm.createQuery("SELECT c FROM "
				+ CommandEntity.class.getName()
				+ " c WHERE c.locked = false ORDER BY ctime");
		query.setMaxResults(1);
		@SuppressWarnings("unchecked")
		List<CommandEntity> cmdList = (List<CommandEntity>) query
				.getResultList();
		if (cmdList.size() > 0) {
			Command first = cmdList.get(0);
			first.setLocked(true);
			pm.persist(first);
			returnValue = first;
		} else {
			returnValue =  NullCommand.INSTANCE;
		}
		pm.close();
		return returnValue;
	}

	@Override
	public Command getById(long callbackId) {
		EntityManager pm = entityManagerProvider.get();
		Command cmd = null;
		try {
			cmd = pm.find(CommandEntity.class, callbackId);
		} finally {
			pm.close();
		}
		return cmd;
	}

	@Override
	public void delete(Command command) {
		Long id = command.getId();
		EntityManager em = entityManagerProvider.get();
		CommandEntity cmd = em.find(CommandEntity.class, id);
		try {
			em.remove(cmd);
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteAll() {
		EntityManager pm = entityManagerProvider.get();
		try {
			Query query = pm.createQuery("SELECT c FROM "
					+ CommandEntity.class.getName() + " AS c ORDER BY ctime");
			query.setMaxResults(maxDelete);
			@SuppressWarnings("unchecked")
			List<CommandEntity> resultList = (List<CommandEntity>) query
					.getResultList();
			for (CommandEntity cmdEntity : resultList) {
				pm.remove(cmdEntity);
			}
		} finally {
			pm.close();
		}
	}
}
