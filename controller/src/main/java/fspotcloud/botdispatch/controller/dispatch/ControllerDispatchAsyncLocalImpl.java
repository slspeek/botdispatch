package fspotcloud.botdispatch.controller.dispatch;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.DispatchException;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class ControllerDispatchAsyncLocalImpl implements ControllerDispatchAsync{

	final private Dispatch dispatch;
	final private Injector injector;

	@Inject
	public ControllerDispatchAsyncLocalImpl(Dispatch dispatch, Injector injector) {
		super();
		this.dispatch = dispatch;
		this.injector = injector;
	}

	public <A extends Action<R>, R extends Result> void execute(A action,
			AsyncCallback<R> callback) {
		injector.injectMembers(callback);
		try {
			R result = dispatch.execute(action);
			callback.onSuccess(result);
		} catch (DispatchException e) {
			callback.onFailure(e);
		}
	}
	
	@Override
	public int getPendingCommands() {
		throw new UnsupportedOperationException();
	}
}
