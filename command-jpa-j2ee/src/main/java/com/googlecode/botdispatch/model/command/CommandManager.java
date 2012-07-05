package com.googlecode.botdispatch.model.command;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.googlecode.botdispatch.AsyncCallback;
import com.googlecode.botdispatch.model.api.Command;
import com.googlecode.botdispatch.model.command.jpa.CommandEntity;
import net.customware.gwt.dispatch.shared.Action;

import javax.inject.Named;
import javax.persistence.EntityManager;

public class CommandManager extends CommandManagerBase<Command, CommandEntity> {


    @Override
    public Command newEntity(Action action, AsyncCallback callback) {
        return new CommandEntity(action, callback);
    }

    @Override
    public Class<CommandEntity> getEntityType() {
        return CommandEntity.class;
    }
}
