package fspotcloud.botdispatch.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.bot.Bot;
import fspotcloud.botdispatch.controller.dispatch.DefaultControllerDispatchAsync;
import fspotcloud.botdispatch.controller.inject.ControllerModule;
import fspotcloud.botdispatch.model.CommandModelModule;
import fspotcloud.botdispatch.model.DatastoreTest;
public class IntegrationTest extends DatastoreTest {

	Injector injector;

	TestAction action = new TestAction("Your name here");
	SecondAction secondAction = new SecondAction("gnu");
	ThrowingAction throwing = new ThrowingAction("Demian");
	TestAsyncCallback callback;
	String resultMessage = "Hello to you, Your name here";
	HeavyReport report;
	DefaultControllerDispatchAsync dispatch;
	Bot bot;
	ArgumentCaptor<DispatchException> captor;

	@Override
	public void setUp() {
		callback  = new TestAsyncCallback();
		report = mock(HeavyReport.class);
		 
		injector = Guice.createInjector(new LocalBotModule(),
				new ActionsModule(), new CommandModelModule(),
				new ControllerModule(), new HeavyReportModule(report));
		bot = injector.getInstance(Bot.class);
		dispatch = injector.getInstance(DefaultControllerDispatchAsync.class);
		captor = ArgumentCaptor.forClass(DispatchException.class);
		super.setUp();
	}

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
