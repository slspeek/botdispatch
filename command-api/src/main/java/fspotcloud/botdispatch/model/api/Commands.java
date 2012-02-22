package fspotcloud.botdispatch.model.api;

import com.google.gwt.user.client.rpc.AsyncCallback;
import fspotcloud.simplejpadao.SimpleDAOGenId;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public interface Commands extends SimpleDAOGenId<Command,Long> {

	Command createAndSave(Action<?> action, AsyncCallback<? extends Result> callback);

	Command getById(long callbackId);
	
	int getCountUnderAThousend();

	void deleteAll();

	Command getAndLockFirstCommand();

}