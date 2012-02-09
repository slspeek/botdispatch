package fspotcloud.botdispatch.controller.callback;

import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.botdispatch.model.api.Command;

public class ErrorHandlerImpl {

	final private Throwable error;
	final private Command command;
	final private Injector injector;

	@Inject
	public ErrorHandlerImpl(@Assisted Throwable error, @Assisted Command command, Injector injector) {
		super();
		this.error = error;
		this.command = command;
		this.injector = injector;
	}


	public void onError() {
		AsyncCallback<? extends Result> callback = command.getCallback();
		if (injector != null) {
			injector.injectMembers(callback);
		}
		callback.onFailure(error);
	}

}
