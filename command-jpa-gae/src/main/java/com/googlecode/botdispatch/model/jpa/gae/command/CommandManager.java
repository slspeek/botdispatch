package com.googlecode.botdispatch.model.jpa.gae.command;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.googlecode.botdispatch.AsyncCallback;
import com.googlecode.botdispatch.model.api.Command;
import com.googlecode.botdispatch.model.api.Commands;
import com.googlecode.botdispatch.model.command.CommandManagerBase;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import javax.inject.Named;
import javax.persistence.EntityManager;

public class CommandManager extends CommandManagerBase<Command, CommandEntity> implements Commands {


    @Override
    public Command newEntity(Action<?> action, AsyncCallback<? extends Result> callback) {
        return new CommandEntity(action, callback);
    }

    @Override
    public Class<CommandEntity> getEntityType() {
        return CommandEntity.class;
    }
}
