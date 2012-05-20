package com.googlecode.botdispatch.controller.callback;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.botdispatch.SerializableAsyncCallback;
import com.googlecode.botdispatch.controller.inject.ControllerModule;
import com.googlecode.botdispatch.controller.inject.NullControllerHook;
import com.googlecode.botdispatch.model.DatastoreTest;
import com.googlecode.botdispatch.model.api.Command;
import com.googlecode.botdispatch.model.api.Commands;
import com.googlecode.botdispatch.model.jpa.gae.command.CommandModelModule;
import com.googlecode.botdispatch.test.*;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;
import org.apache.commons.lang.SerializationUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class ControllerIntegrationTest extends DatastoreTest {

    DispatchException error = new ActionException("Wrong");
    Commands commandManager;
    TestAction action = new TestAction("Your name here");
    TestResult result = new TestResult("Hey you");
    ThrowingAction throwing = new ThrowingAction("Demian");
    byte[] serializedResult;
    byte[] serializedError;
    SerializableAsyncCallback<TestResult> callback = new TestAsyncCallback();
    Injector injector;
    HeavyReport report;
    Controller controller;
    ResultHandlerFactory handlerFactory;
    ErrorHandlerFactory errorHandlerFactory;
    ArgumentCaptor<Throwable> captor;

    @Before
    public void setUp() {
        super.setUp();
        System.setProperty("appengine.orm.disable.duplicate.emf.exception", "true");
        report = mock(HeavyReport.class);
        injector = Guice.createInjector(new ControllerModule(),
                new HeavyReportModule(report), new CommandModelModule());
        handlerFactory = injector.getInstance(ResultHandlerFactory.class);
        errorHandlerFactory = injector.getInstance(ErrorHandlerFactory.class);
        captor = ArgumentCaptor.forClass(Throwable.class);
        commandManager = injector.getInstance(Commands.class);
        controller = new Controller(commandManager, handlerFactory,
                errorHandlerFactory, new NullControllerHook());
        serializedResult = SerializationUtils.serialize(result);
        serializedError = SerializationUtils.serialize(error);
    }

    @Test
    public void testCallback() throws IOException {
        Command cmd = commandManager.createAndSave(action, callback);
        Object[] back = controller.callback(cmd.getId(), serializedResult);
        assertEquals(-1L, back[0]);
        verify(report).report("Hey you");
    }

    @Test
    public void testOnError() throws IOException {
        Command cmd = commandManager.createAndSave(throwing, callback);
        Object[] back = controller.callback(cmd.getId(), serializedError);
        // assertEquals(-1L, back[0]);
        verify(report).error(captor.capture());
    }

    @Test
    public void testDoubleCallback() throws IOException {
        Command cmd1 = commandManager.createAndSave(action, callback);
        Command cmd2 = commandManager.createAndSave(action, callback);
        Object[] back = controller.callback(cmd1.getId(), serializedResult);
        assertEquals(cmd2.getId(), back[0]);
        verify(report).report("Hey you");
    }
}
