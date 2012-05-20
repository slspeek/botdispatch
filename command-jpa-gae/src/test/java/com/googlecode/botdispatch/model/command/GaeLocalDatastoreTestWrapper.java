package com.googlecode.botdispatch.model.command;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.common.testing.TearDown;
import com.google.common.testing.TearDownAccepter;
import com.google.guiceberry.TestWrapper;

import javax.inject.Inject;

public class GaeLocalDatastoreTestWrapper implements TestWrapper {

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
            new LocalDatastoreServiceTestConfig());
    @Inject
    TearDownAccepter tearDownAccepter;

    public void toRunBeforeTest() {
        helper.setUp();
        tearDownAccepter.addTearDown(new TearDown() {

            public void tearDown() throws Exception {
                helper.tearDown();
            }
        });
    }
}
