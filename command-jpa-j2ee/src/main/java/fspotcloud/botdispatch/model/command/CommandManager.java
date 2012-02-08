package fspotcloud.botdispatch.model.command;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.command.jpa.CommandEntity;
import javax.inject.Named;
import javax.persistence.EntityManager;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public class CommandManager extends CommandManagerBase {


    @Inject
    public CommandManager(Provider<EntityManager> entityManagerProvider,
            @Named("maxCommandDelete") Integer maxDelete) {
        super(entityManagerProvider, maxDelete);
        
       }

    @Override
    Class<? extends Command> getEntityClass() {
    return CommandEntity.class;
    }

    @Override
    Command newEntity(Action<?> action, AsyncCallback<? extends Result> callback) {
        return new CommandEntity(action, callback);
    }

    
}
