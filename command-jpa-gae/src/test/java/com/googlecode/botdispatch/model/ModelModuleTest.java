package com.googlecode.botdispatch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.botdispatch.model.jpa.gae.command.GaeCommandModelModule;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ModelModuleTest extends TestCase {

    GaeCommandModelModule module = new GaeCommandModelModule();

    public static TestSuite suite() {
        return new TestSuite(ModelModuleTest.class);
    }

    public void testInjector() {
        Injector injector = Guice.createInjector(module);
        assertNotNull(injector);
    }
}
