package com.googlecode.botdispatch.test;

import com.google.inject.Module;
import com.googlecode.botdispatch.model.command.jpa.J2eeCommandModelModule;


public class J2eeIntegrationTest extends IntegrationTest {

    @Override
    protected Module getCommandModule() {
        return new J2eeCommandModelModule();
    }
}
