package com.googlecode.botdispatch.model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.botdispatch.model.command.jpa.J2eeCommandModelModule;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ModelModuleTest extends TestCase {

    J2eeCommandModelModule module = new J2eeCommandModelModule();

    public static TestSuite suite() {
        return new TestSuite(ModelModuleTest.class);
    }

    public void testInjector() {
        Injector injector = Guice.createInjector(module);
        assertNotNull(injector);
    }
}
