package com.googlecode.botdispatch.testbot;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.googlecode.botdispatch.bot.Bot;
import com.googlecode.botdispatch.bot.BotModule;
import com.googlecode.botdispatch.test.ActionsModule;

public class Main {
	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new BotModule(), new ActionsModule(), new TestModule());

		StopListener stopListener = injector.getInstance(StopListener.class);
	    stopListener.start();
		
	    Bot bot = injector.getInstance(Bot.class); 
	    bot.runForever();
	}
}
