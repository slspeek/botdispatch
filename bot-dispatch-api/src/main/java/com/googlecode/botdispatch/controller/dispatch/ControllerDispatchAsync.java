package com.googlecode.botdispatch.controller.dispatch;

import com.googlecode.botdispatch.SerializableAsyncCallback;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public interface ControllerDispatchAsync {

    <A extends Action<R>, R extends Result> void execute(A action,
                                                         SerializableAsyncCallback<R> callback);

    int getPendingCommands();
}
