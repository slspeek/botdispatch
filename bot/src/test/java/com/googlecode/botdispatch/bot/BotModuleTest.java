package com.googlecode.botdispatch.bot;

import com.googlecode.botdispatch.bot.BotModule;
import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.googlecode.botdispatch.test.ActionsModule;

public class BotModuleTest extends TestCase {

	public void testInjector() {
		Injector injector = Guice.createInjector(new BotModule(), new ActionsModule());
		assertNotNull(injector);
	}
}
