package com.googlecode.botdispatch.test;

import com.googlecode.botdispatch.test.TestAsyncCallback;
import com.googlecode.botdispatch.test.ThrowingAction;
import com.googlecode.botdispatch.test.SecondAction;
import com.googlecode.botdispatch.test.TestAction;
import com.googlecode.botdispatch.test.HeavyReport;
import com.google.guiceberry.junit4.GuiceBerryRule;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;

import com.google.inject.Inject;

import com.googlecode.botdispatch.bot.Bot;
import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class IntegrationTest {

    @Rule
    public GuiceBerryRule guiceBerry = new GuiceBerryRule(BotDispatchGuiceberryEnv.class);
   
    TestAction action = new TestAction("Your name here");
    SecondAction secondAction = new SecondAction("gnu");
    ThrowingAction throwing = new ThrowingAction("Demian");
    TestAsyncCallback callback = new TestAsyncCallback();
    String resultMessage = "Hello to you, Your name here";
    HeavyReport report = new HeavyReport();
    @Inject
    ControllerDispatchAsync dispatch;
    @Inject
    Bot bot;
    @Captor
    ArgumentCaptor<DispatchException> captor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOne() throws InterruptedException {
        dispatch.execute(throwing, callback);
        dispatch.execute(secondAction, callback);
        dispatch.execute(action, callback);
        bot.runForever(2);
       Assert.assertEquals(resultMessage, report.report);
    }
}