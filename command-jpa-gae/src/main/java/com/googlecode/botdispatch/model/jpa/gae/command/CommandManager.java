package com.googlecode.botdispatch.model.jpa.gae.command;


import javax.inject.Named;
import javax.persistence.EntityManager;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;

import com.googlecode.botdispatch.model.api.Command;
import com.googlecode.botdispatch.model.api.Commands;
import com.googlecode.botdispatch.model.command.CommandManagerBase;

public class CommandManager extends CommandManagerBase<Command, CommandEntity> implements Commands {

    @Inject
    public CommandManager(Provider<EntityManager> entityManagerProvider,
            @Named("maxCommandDelete") Integer maxDelete) {
        super(CommandEntity.class, entityManagerProvider, maxDelete);
    }

    @Override
    public Command newEntity(Action<?> action, AsyncCallback<? extends Result> callback) {
        return new CommandEntity(action, callback);
    }
}
