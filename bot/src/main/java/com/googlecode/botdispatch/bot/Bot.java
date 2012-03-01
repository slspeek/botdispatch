package com.googlecode.botdispatch.bot;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

public class Bot {
	final static private Logger log = Logger.getLogger(Bot.class.getName());

	final private Provider<BotDispatchServer> serverProvider;

	final private Pauser pauser;
	final private int pause;

	@Inject
	public Bot(Provider<BotDispatchServer> serverProvider, Pauser pauser,
			@Named("pause") int pause) {
		super();
		this.serverProvider = serverProvider;
		this.pause = pause;
		this.pauser = pauser;
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
				log.log(Level.SEVERE, "Exception in main-loop, restarting in "
						+ pause + "s", e);
				Thread.sleep(pause * 1000);
			}
		}
	}

}
