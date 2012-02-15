package fspotcloud.botdispatch.model.command;

import com.google.gwt.user.client.rpc.AsyncCallback;
import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.api.NullCommand;
import fspotcloud.simplejpadao.SimpleDAOGenIdImpl;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public abstract class CommandManagerBase<T extends Command, U extends T> extends SimpleDAOGenIdImpl<Command, U>  implements Commands {
    
    protected final Provider<EntityManager> entityManagerProvider;
    protected Integer maxDelete;
    
    @Inject
    public CommandManagerBase(Class<U> entityType, Provider<EntityManager> entityManagerProvider,
            @Named("maxCommandDelete") Integer maxDelete) {
        super(entityType, entityManagerProvider);
        this.entityManagerProvider = entityManagerProvider;
        this.maxDelete = maxDelete;
    }
    
    public int getCountUnderAThousend() {
        return count(1000);
    }
    
    public Command createAndSave(Action<?> action,
            AsyncCallback<? extends Result> callback) {
        Command cmd = newEntity(action, callback);
        save(cmd);
        return cmd;
    }

    public Command getAndLockFirstCommand() {
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.getTransaction().begin();
        Command returnValue;
        Query query = entityManager.createQuery("SELECT c FROM "
                + getEntityClass().getName()
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
    
    public Command getById(long callbackId) {
        return (Command) find(callbackId);
    }
    
    
    public void deleteAll() {
        deleteBulk(maxDelete);
    }
    
    public Class<? extends Command> getEntityClass() {
        return (Class<? extends Command>) entityType;
    }
    
    public abstract Command newEntity(Action<?> action, AsyncCallback<? extends Result> callback);
}