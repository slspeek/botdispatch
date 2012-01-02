package fspotcloud.botdispatch.model.api;

import java.util.Date;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.botdispatch.model.api.Command;

public class NullCommand implements Command {

	public static Command INSTANCE = new NullCommand();
	@Override
	public Long getId() {
		return -1L;
	}

	@Override
	public Action<?> getAction() {
		return null;
	}

	@Override
	public AsyncCallback<Result> getCallback() {
		return null;
	}

	@Override
	public Date getCtime() {
		return null;
	}

	@Override
	public boolean isLocked() {
		return false;
	}

	@Override
	public void setLocked(boolean b) {
		// TODO Auto-generated method stub
		
	}


}
