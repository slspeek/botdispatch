package com.googlecode.botdispatch.bot;

import com.google.inject.Provider;
import junit.framework.TestCase;

import static org.mockito.Mockito.*;

public class BotTest extends TestCase {

    private static final int PAUSE = 0;
    BotDispatchServer server;
    Provider<BotDispatchServer> provider;
    Bot target;
    Pauser pauser;

    @Override
    protected void setUp() throws Exception {
        server = mock(BotDispatchServer.class);
        pauser = mock(Pauser.class);
        provider = new Provider<BotDispatchServer>() {

            @Override
            public BotDispatchServer get() {
                return server;
            }

        };
        target = new Bot(provider, PAUSE);
        super.setUp();
    }


    public void testRunOnce() throws Exception {
        target.runForever(1);
        verify(server).runForever(1);
    }

    public void testRunOnceOnException() throws Exception {
        doThrow(new RuntimeException()).when(server).runForever(1);
        target.runForever(1);
        verify(server).runForever(1);
    }

    public void testRunTwiceOnException() throws Exception {
        doThrow(new RuntimeException()).when(server).runForever(2);
        target.runForever(2);
        verify(server, times(2)).runForever(2);
    }

}
