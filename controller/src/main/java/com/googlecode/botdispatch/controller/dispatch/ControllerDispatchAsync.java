package com.googlecode.botdispatch.controller.dispatch;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ControllerDispatchAsync {
	<A extends Action<R>, R extends Result> void execute(A action,
			AsyncCallback<R> callback);
	
	int getPendingCommands();
}
