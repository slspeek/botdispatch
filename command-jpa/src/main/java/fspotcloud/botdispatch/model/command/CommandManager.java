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

	private final Provider<EntityManager> entityManager;
	private Integer maxDelete;

	@Inject
	public CommandManager(Provider<EntityManager> pmProvider,
			@Named("maxCommandDelete") Integer maxDelete) {
		this.entityManager = pmProvider;
		this.maxDelete = maxDelete;
	}

	public void save(Command c) {
		EntityManager em = entityManager.get();
		try {
			em.persist(c);
		} finally {
			em.close();
		}
	}

	@Override
	public int getCountUnderAThousend() {
		EntityManager em = entityManager.get();
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
		try {

			cmd = new CommandEntity(action, (AsyncCallback<Result>) callback);
		} catch (IOException e) {
			e.printStackTrace();
		}
		save(cmd);
		return cmd;
	}

	@Override
	public Command getAndLockFirstCommand() {
		EntityManager pm = entityManager.get();
		try {
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
		EntityManager pm = entityManager.get();
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
		EntityManager em = entityManager.get();
		CommandEntity cmd = em.find(CommandEntity.class, id);
		try {
			em.remove(cmd);
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteAll() {
		EntityManager pm = entityManager.get();
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