package com.googlecode.botdispatch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.botdispatch.model.jpa.gae.command.CommandModelModule;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ModelModuleTest extends TestCase {

    CommandModelModule module = new CommandModelModule();

    public static TestSuite suite() {
        return new TestSuite(ModelModuleTest.class);
    }

    public void testInjector() {
        Injector injector = Guice.createInjector(module);
        assertNotNull(injector);
    }
}
