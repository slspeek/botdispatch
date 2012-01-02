package fspotcloud.botdispatch.bot;

import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.test.ActionsModule;

public class BotModuleTest extends TestCase {

	public void testInjector() {
		Injector injector = Guice.createInjector(new BotModule(), new ActionsModule());
		assertNotNull(injector);
	}
}
