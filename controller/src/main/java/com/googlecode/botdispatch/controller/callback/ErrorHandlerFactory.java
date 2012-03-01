package com.googlecode.botdispatch.controller.callback;

import com.googlecode.botdispatch.model.api.Command;

public interface ErrorHandlerFactory {
	ErrorHandlerImpl get(Throwable result, Command command);
}
