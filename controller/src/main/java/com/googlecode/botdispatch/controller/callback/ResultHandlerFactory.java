package com.googlecode.botdispatch.controller.callback;

import net.customware.gwt.dispatch.shared.Result;

import com.google.inject.assistedinject.Assisted;

import com.googlecode.botdispatch.model.api.Command;

public interface ResultHandlerFactory {
	ResultHandlerImpl get(@Assisted Result result, @Assisted Command command);
}
