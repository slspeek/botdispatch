package com.googlecode.botdispatch.test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Module;
import com.googlecode.botdispatch.model.jpa.gae.command.GaeCommandModelModule;
import org.junit.Before;


public class GaeIntegrationTest extends IntegrationTest {
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void before() {
        helper.setUp();
    }

    public void tearDown() {
        helper.tearDown();
    }
    @Override
    protected Module getCommandModule() {
        return new GaeCommandModelModule();
    }
}
