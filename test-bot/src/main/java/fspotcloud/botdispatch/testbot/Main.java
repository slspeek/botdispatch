package fspotcloud.botdispatch.testbot;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.bot.Bot;
import fspotcloud.botdispatch.bot.BotModule;
import fspotcloud.botdispatch.test.ActionsModule;

public class Main {
	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new BotModule(), new ActionsModule(), new TestModule());

		StopListener stopListener = injector.getInstance(StopListener.class);
	    stopListener.start();
		
	    Bot bot = injector.getInstance(Bot.class); 
	    bot.runForever();
	}
}
