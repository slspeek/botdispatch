package com.googlecode.botdispatch.controller.dispatch;

import com.google.inject.Inject;
import com.googlecode.botdispatch.SerializableAsyncCallback;
import com.googlecode.botdispatch.model.api.Commands;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public class DefaultControllerDispatchAsync implements ControllerDispatchAsync {

    private final Commands commandManager;

    @Inject
    public DefaultControllerDispatchAsync(Commands commandManager) {
        super();
        this.commandManager = commandManager;
    }

    public <A extends Action<R>, R extends Result> void execute(A action,
                                                                SerializableAsyncCallback<R> callback) {
        commandManager.createAndSave(action, callback);
    }


    @Override
    public int getPendingCommands() {
        return commandManager.getCountUnderAThousend();
    }
}
