package com.googlecode.botdispatch.controller.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;
import com.googlecode.botdispatch.model.jpa.gae.command.GaeCommandModelModule;
import junit.framework.TestCase;

public class ControllerModuleTest extends TestCase {

    ControllerModule module = new ControllerModule();
    GaeCommandModelModule cmdModule = new GaeCommandModelModule();

    public void testConfigure() {
        Injector i = Guice.createInjector(module, cmdModule);
        ControllerDispatchAsync dispatch = i.getInstance(ControllerDispatchAsync.class);
        assertNotNull(dispatch);
    }

}
