package com.googlecode.botdispatch.controller.inject;

import com.google.inject.servlet.ServletModule;
import com.googlecode.botdispatch.controller.callback.ControllerServlet;

public class ControllerServletModule extends ServletModule {

    private final String botSecret;

    public ControllerServletModule(String botSecret) {
        this.botSecret = botSecret;
    }

    @Override
    protected void configureServlets() {
        serve("/controller/" + botSecret).with(ControllerServlet.class);
    }
}
