package com.googlecode.botdispatch.bot;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bot {
    final static private Logger log = Logger.getLogger(Bot.class.getName());

    final private Provider<BotDispatchServer> serverProvider;

    final private int pause;

    @Inject
    public Bot(Provider<BotDispatchServer> serverProvider,
               @Named("pause") int pause) {
        super();
        this.serverProvider = serverProvider;
        this.pause = pause;
    }

    public void runForever() throws InterruptedException {
        runForever(Integer.MAX_VALUE);
    }

    public void runForever(int n) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            BotDispatchServer server = serverProvider.get();
            try {
                server.runForever(n);
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception in main-loop: ", e);
//                Thread.sleep(pause * 1000);
            }
        }
    }

}
