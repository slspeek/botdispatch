package fspotcloud.botdispatch.controller.inject;

import com.google.inject.AbstractModule;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsyncLocalImpl;


public class LocalControllerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ControllerDispatchAsync.class).to(ControllerDispatchAsyncLocalImpl.class);
	}
	
}
