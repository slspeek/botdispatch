package fspotcloud.botdispatch.model.command;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.api.NullCommand;
import fspotcloud.botdispatch.model.command.jpa.CommandEntity;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public class CommandManager implements Commands {

    private final Provider<EntityManager> entityManagerProvider;
    private Integer maxDelete;

    @Inject
    public CommandManager(Provider<EntityManager> entityManagerProvider,
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
                + CommandEntity.class.getName() + " AS c");
        @SuppressWarnings("unchecked")
        List<CommandEntity> rs = (List<CommandEntity>) query.getResultList();
        count = rs.size();
        entityManager.getTransaction().commit();
        //entityManager.close();
        return count;
    }

    @Override
    public CommandEntity createAndSave(Action<?> action,
            AsyncCallback<? extends Result> callback) {
        CommandEntity cmd = new CommandEntity(action, callback);
        save(cmd);
        return cmd;
    }

    @Override
    public Command getAndLockFirstCommand() {
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.getTransaction().begin();
        Command returnValue;
        Query query = entityManager.createQuery("SELECT c FROM "
                + CommandEntity.class.getName()
                + " c WHERE c.locked = false ORDER BY ctime");
        query.setMaxResults(1);
        @SuppressWarnings("unchecked")
        List<CommandEntity> cmdList = (List<CommandEntity>) query.getResultList();
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
        Command cmd = entityManager.find(CommandEntity.class, callbackId);
        entityManager.getTransaction().commit();
        return cmd;
    }

    @Override
    public void delete(Command command) {
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.getTransaction().begin();
        Long id = command.getId();
        CommandEntity cmd = entityManager.find(CommandEntity.class, id);
        entityManager.remove(cmd);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = entityManagerProvider.get();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("SELECT c FROM "
                + CommandEntity.class.getName() + " AS c ORDER BY ctime");
        query.setMaxResults(maxDelete);
        @SuppressWarnings("unchecked")
        List<CommandEntity> resultList = (List<CommandEntity>) query.getResultList();
        for (CommandEntity cmdEntity : resultList) {
            entityManager.remove(cmdEntity);
        }
        entityManager.getTransaction().commit();
    }
}
