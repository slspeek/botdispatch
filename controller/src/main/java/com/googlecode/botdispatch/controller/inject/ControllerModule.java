package com.googlecode.botdispatch.controller.inject;

import com.googlecode.botdispatch.controller.callback.ControllerHook;


public class ControllerModule extends AbstractControllerModule {

	@Override
	protected void configure() {
		super.configure();
		bind(ControllerHook.class).to(NullControllerHook.class);
	}
	
}
