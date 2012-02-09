package fspotcloud.botdispatch.controller.callback;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.command.CommandDO;
import fspotcloud.botdispatch.test.HeavyReport;
import fspotcloud.botdispatch.test.HeavyReportModule;
import fspotcloud.botdispatch.test.TestAction;
import fspotcloud.botdispatch.test.TestAsyncCallback;
import fspotcloud.botdispatch.test.TestResult;

public class ResultHandlerImplTest extends TestCase {

	Command cmd;
	TestAsyncCallback callback;
	TestResult result;
	TestAction action;
	ResultHandlerImpl target;
	Injector injector;
	HeavyReport report;
	Commands commandManager;

	@Override
	protected void setUp() throws Exception {
		report = mock(HeavyReport.class);
		commandManager = mock(Commands.class);
		injector = Guice.createInjector(new HeavyReportModule(report));
		action = new TestAction("You");
		result = new TestResult("Hi there");
		callback = new TestAsyncCallback();
		cmd = new CommandDO(action, callback);
		target = new ResultHandlerImpl(result, cmd, injector, commandManager);
		super.setUp();
	}

	public void testCallback() {
		target.callback();
		assertEquals("Hi there", callback.getResult().getMessage());
		verify(report).report("Hi there");
		verify(commandManager).delete(cmd);
	}

}
