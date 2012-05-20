package com.googlecode.botdispatch.controller.callback;

import com.google.inject.assistedinject.Assisted;
import com.googlecode.botdispatch.model.api.Command;
import net.customware.gwt.dispatch.shared.Result;

public interface ResultHandlerFactory {
    ResultHandlerImpl get(@Assisted Result result, @Assisted Command command);
}
