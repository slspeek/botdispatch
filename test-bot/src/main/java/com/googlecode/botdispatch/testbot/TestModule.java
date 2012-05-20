package com.googlecode.botdispatch.testbot;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StopListener.class);
        bind(Integer.class).annotatedWith(Names.named("stop port")).toInstance(
                Integer.valueOf(System.getProperty("stop.port", "4444")));
    }

}
