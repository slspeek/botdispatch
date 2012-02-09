package fspotcloud.botdispatch.controller.callback;

import fspotcloud.botdispatch.model.api.Command;

public interface ErrorHandlerFactory {
	ErrorHandlerImpl get(Throwable result, Command command);
}
