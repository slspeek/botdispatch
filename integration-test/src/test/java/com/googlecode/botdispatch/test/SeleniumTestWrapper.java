package com.googlecode.botdispatch.test;

import com.google.common.testing.TearDown;
import com.google.common.testing.TearDownAccepter;
import com.google.guiceberry.TestWrapper;
import com.thoughtworks.selenium.Selenium;

import javax.inject.Inject;

public class SeleniumTestWrapper implements TestWrapper {

    @Inject
    Selenium selenium;
    @Inject
    TearDownAccepter tearDownAccepter;

    public void toRunBeforeTest() {
        tearDownAccepter.addTearDown(new TearDown() {

            public void tearDown() throws Exception {
                selenium.close();
            }
        });
    }
}
