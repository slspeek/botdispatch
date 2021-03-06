package com.googlecode.botdispatch.bot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.botdispatch.test.ActionsModule;
import com.googlecode.botdispatch.test.TestAction;
import com.googlecode.botdispatch.test.TestResult;
import junit.framework.TestCase;
import org.apache.commons.lang.SerializationUtils;

import static org.mockito.Mockito.*;

public class BotDispatchServerTest extends TestCase {

    Pauser pauser;
    CommandWorkerFactory factory;
    Injector injector = Guice.createInjector(new BotModule(),
            new ActionsModule());
    RemoteExecutor remote;
    BotDispatchServerImpl target;
    TestAction action = new TestAction("Josie");
    TestResult result = new TestResult("Hello to you, Josie");
    private byte[] serializedAction;
    private byte[] serializedResult;

    protected void setUp() throws Exception {
        pauser = mock(Pauser.class);
        remote = mock(RemoteExecutor.class);
        factory = injector.getInstance(CommandWorkerFactory.class);
        serializedAction = SerializationUtils.serialize(action);
        serializedResult = SerializationUtils.serialize(result);
        target = new BotDispatchServerImpl(remote, factory, pauser);
        super.setUp();
    }

    public void testEmptyStart() throws Exception {
        when(remote.execute(-1L, null)).thenReturn(new Object[]{-1L, null});
        target.runForever(1);
        verify(pauser).pause();
    }

    public void testStart() throws Exception {
        when(remote.execute(-1L, null)).thenReturn(new Object[]{1000L, serializedAction});
        when(remote.execute(1000L, serializedResult)).thenReturn(new Object[]{-1L, null});
        target.runForever(2);
        verify(remote).execute(1000L, serializedResult);
        verify(pauser).pause();
    }
}
