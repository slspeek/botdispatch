package com.googlecode.botdispatch.controller.inject;

import com.google.inject.AbstractModule;
import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;
import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsyncLocalImpl;


public class LocalControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ControllerDispatchAsync.class).to(ControllerDispatchAsyncLocalImpl.class);
    }

}
