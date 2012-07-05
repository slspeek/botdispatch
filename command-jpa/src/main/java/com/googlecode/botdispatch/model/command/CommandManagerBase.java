package com.googlecode.botdispatch.model.command;

import com.googlecode.botdispatch.AsyncCallback;
import com.googlecode.botdispatch.model.api.Command;
import com.googlecode.botdispatch.model.api.Commands;
import com.googlecode.botdispatch.model.api.NullCommand;
import com.googlecode.simplejpadao.SimpleDAOGenIdImpl;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public abstract class CommandManagerBase<T extends Command, U extends T>
        extends SimpleDAOGenIdImpl<Command, U, Long> implements Commands {

    @Inject   @Named("maxCommandDelete")
    protected Integer maxDelete;

    @Override
    public int getCountUnderAThousend() {
        return count(1000);
    }

    @Override
    public Command createAndSave(Action<?> action,
                                 AsyncCallback<? extends Result> callback) {
        Command cmd = newEntity(action, callback);
        save(cmd);
        return cmd;
    }

    @Override
    public Command getAndLockFirstCommand() {
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.getTransaction().begin();
        Command returnValue;
        Query query = entityManager.createQuery("SELECT c FROM "
                + getEntityType().getName()
                + " c WHERE c.locked = false ORDER BY ctime");
        query.setMaxResults(1);
        @SuppressWarnings("unchecked")
        List<Command> cmdList = (List<Command>) query.getResultList();
        if (cmdList.size() > 0) {
            Command first = cmdList.get(0);
            first.setLocked(true);
            entityManager.persist(first);
            returnValue = first;
        } else {
            returnValue = NullCommand.INSTANCE;
        }
        entityManager.getTransaction().commit();
        entityManager.close();
        return returnValue;
    }

    @Override
    public Command getById(long callbackId) {
        return (Command) find(callbackId);
    }


    @Override
    public void deleteAll() {
        deleteBulk(maxDelete);
    }
    public abstract Command newEntity(Action<?> action, AsyncCallback<? extends Result> callback);
}
