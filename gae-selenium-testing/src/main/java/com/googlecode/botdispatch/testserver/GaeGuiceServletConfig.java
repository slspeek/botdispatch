package com.googlecode.botdispatch.testserver;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.googlecode.botdispatch.controller.inject.ControllerModule;
import com.googlecode.botdispatch.controller.inject.ControllerServletModule;
import com.googlecode.botdispatch.model.jpa.gae.command.GaeCommandModelModule;

import java.util.ArrayList;
import java.util.List;

public class GaeGuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        Injector i = Guice.createInjector(new ControllerModule(),
                new GaeCommandModelModule(), new TestServletModule("SECRET"),
                new TestModule());
        return i;
    }

    private class TestServletModule extends ControllerServletModule {

        public TestServletModule(String botSecret) {
            super(botSecret);
        }

        @Override
        protected void configureServlets() {
            super.configureServlets();
            serve("/test").with(TestServlet.class);
        }
    }

    private class TestModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(List.class).toInstance(new ArrayList<String>());

        }

    }

}
