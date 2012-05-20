package com.googlecode.botdispatch.test;

import com.google.guiceberry.GuiceBerryModule;
import com.googlecode.botdispatch.controller.inject.ControllerModule;
import com.googlecode.botdispatch.model.command.jpa.CommandModelModule;

public class BotDispatchGuiceberryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        install(new CommandModelModule());
        install(new LocalBotModule());
        install(new ActionsModule());
        install(new ControllerModule());
    }
}
