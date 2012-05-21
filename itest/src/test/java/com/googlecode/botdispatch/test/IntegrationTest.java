package com.googlecode.botdispatch.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.googlecode.botdispatch.bot.Bot;
import com.googlecode.botdispatch.controller.dispatch.DefaultControllerDispatchAsync;
import com.googlecode.botdispatch.controller.inject.ControllerModule;
import net.customware.gwt.dispatch.shared.DispatchException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public abstract class IntegrationTest {

    private TestAction action = new TestAction("Your name here");
    SecondAction secondAction = new SecondAction("gnu");
    ThrowingAction throwing = new ThrowingAction("Demian");
    TestAsyncCallback callback;
    String resultMessage = "Hello to you, Your name here";
    HeavyReport report;
    DefaultControllerDispatchAsync dispatch;
    Bot bot;
    ArgumentCaptor<DispatchException> captor;

    @Before
    public final void setUp() {
        callback = new TestAsyncCallback();
        report = mock(HeavyReport.class);

        Injector injector = Guice.createInjector(new LocalBotModule(),
                new ActionsModule(), getCommandModule(),
                new ControllerModule(), new HeavyReportModule(report));
        bot = injector.getInstance(Bot.class);
        dispatch = injector.getInstance(DefaultControllerDispatchAsync.class);
        captor = ArgumentCaptor.forClass(DispatchException.class);

    }

    protected abstract Module getCommandModule();

    @Test
    public void testOne() throws InterruptedException {
        dispatch.execute(action, callback);
        dispatch.execute(secondAction, callback);
        dispatch.execute(throwing, callback);
        bot.runForever(4);
        verify(report).report(resultMessage);
        verify(report).report("GNU");
        verify(report).error(captor.capture());

    }
}
