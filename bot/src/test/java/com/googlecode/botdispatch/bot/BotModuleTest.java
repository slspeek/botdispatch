package com.googlecode.botdispatch.bot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.botdispatch.test.ActionsModule;
import junit.framework.TestCase;

public class BotModuleTest extends TestCase {

    public void testInjector() {
        Injector injector = Guice.createInjector(new BotModule(), new ActionsModule());
        assertNotNull(injector);
    }
}
