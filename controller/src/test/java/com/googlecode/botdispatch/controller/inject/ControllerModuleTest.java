package com.googlecode.botdispatch.controller.inject;

import com.googlecode.botdispatch.controller.inject.ControllerModule;
import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;
import com.googlecode.botdispatch.model.jpa.gae.command.CommandModelModule;

public class ControllerModuleTest extends TestCase {

	ControllerModule module = new ControllerModule();
	CommandModelModule cmdModule = new CommandModelModule();
	public void testConfigure() {
		Injector i = Guice.createInjector(module, cmdModule);
		ControllerDispatchAsync dispatch = i.getInstance(ControllerDispatchAsync.class);
		assertNotNull(dispatch);
	}

}