package fspotcloud.botdispatch.controller.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import fspotcloud.botdispatch.controller.callback.Controller;
import fspotcloud.botdispatch.controller.callback.ErrorHandlerFactory;
import fspotcloud.botdispatch.controller.callback.ResultHandlerFactory;
import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.botdispatch.controller.dispatch.DefaultControllerDispatchAsync;

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
