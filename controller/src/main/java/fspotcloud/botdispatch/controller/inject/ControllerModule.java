package fspotcloud.botdispatch.controller.inject;

import fspotcloud.botdispatch.controller.callback.ControllerHook;


public class ControllerModule extends AbstractControllerModule {

	@Override
	protected void configure() {
		super.configure();
		bind(ControllerHook.class).to(NullControllerHook.class);
	}
	
}
