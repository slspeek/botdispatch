package fspotcloud.botdispatch.controller.callback;

import net.customware.gwt.dispatch.shared.Result;

import com.google.inject.assistedinject.Assisted;

import fspotcloud.botdispatch.model.api.Command;

public interface ResultHandlerFactory {
	ResultHandlerImpl get(@Assisted Result result, @Assisted Command command);
}
