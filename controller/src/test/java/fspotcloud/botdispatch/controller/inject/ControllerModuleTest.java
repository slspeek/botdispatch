package fspotcloud.botdispatch.controller.inject;

import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.botdispatch.model.CommandModelModule;

public class ControllerModuleTest extends TestCase {

	ControllerModule module = new ControllerModule();
	CommandModelModule cmdModule = new CommandModelModule();
	public void testConfigure() {
		Injector i = Guice.createInjector(module, cmdModule);
		ControllerDispatchAsync dispatch = i.getInstance(ControllerDispatchAsync.class);
		assertNotNull(dispatch);
	}

}
