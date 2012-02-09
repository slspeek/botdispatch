package fspotcloud.botdispatch.model.command;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.api.NullCommand;
import fspotcloud.botdispatch.model.command.jpa.CommandEntityBase;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public abstract class CommandManagerBase implements Commands {

    private final Provider<EntityManager> entityManagerProvider;
    private Integer maxDelete;

    @Inject
    public CommandManagerBase(Provider<EntityManager> entityManagerProvider,
            @Named("maxCommandDelete") Integer maxDelete) {
        this.entityManagerProvider = entityManagerProvider;
        this.maxDelete = maxDelete;
    }

    public void save(Command c) {
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.getTransaction().begin();
        entityManager.persist(c);
        entityManager.getTransaction().commit();
    }

    @Override
    public int getCountUnderAThousend() {
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.getTransaction().begin();
        int count = -1;
        Query query = entityManager.createQuery("select c.id from "
                + getEntityClass().getName() + " AS c");
        @SuppressWarnings("unchecked")
        List<CommandEntityBase> rs = (List<CommandEntityBase>) query.getResultList();
        count = rs.size();
        entityManager.getTransaction().commit();
        return count;
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
        return returnValue;
    }

    @Override
    public Command getById(long callbackId) {
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.getTransaction().begin();
        Command cmd = entityManager.find(getEntityClass(), callbackId);
        entityManager.getTransaction().commit();
        return cmd;
    }

    @Override
    public void delete(Command command) {
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.getTransaction().begin();
        Long id = command.getId();
        Command cmd = entityManager.find(getEntityClass(), id);
        entityManager.remove(cmd);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT c FROM "
                + getEntityClass().getName() + " AS c ORDER BY ctime");
        query.setMaxResults(maxDelete);
        @SuppressWarnings("unchecked")
        List<Command> resultList = (List<Command>) query.getResultList();
        for (Command cmdEntity : resultList) {
            entityManager.remove(cmdEntity);
        }
        entityManager.getTransaction().commit();
    }
    
    abstract Class<? extends Command> getEntityClass();
    abstract Command newEntity(Action<?> action, AsyncCallback<? extends Result> callback);
    
}
