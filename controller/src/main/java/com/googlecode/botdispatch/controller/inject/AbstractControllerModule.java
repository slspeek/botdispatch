package com.googlecode.botdispatch.controller.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.googlecode.botdispatch.controller.callback.Controller;
import com.googlecode.botdispatch.controller.callback.ErrorHandlerFactory;
import com.googlecode.botdispatch.controller.callback.ResultHandlerFactory;
import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;
import com.googlecode.botdispatch.controller.dispatch.DefaultControllerDispatchAsync;

public abstract class AbstractControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Controller.class).in(Singleton.class);
        bind(ControllerDispatchAsync.class).to(DefaultControllerDispatchAsync.class);
        install(new FactoryModuleBuilder()
                .build(ResultHandlerFactory.class));
        install(new FactoryModuleBuilder()
                .build(ErrorHandlerFactory.class));

    }


}
