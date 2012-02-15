package fspotcloud.botdispatch.controller.callback;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.jpa.gae.command.CommandEntity;
import fspotcloud.botdispatch.test.HeavyReport;
import fspotcloud.botdispatch.test.HeavyReportModule;
import fspotcloud.botdispatch.test.TestAction;
import fspotcloud.botdispatch.test.TestAsyncCallback;
import fspotcloud.botdispatch.test.TestResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResultHandlerImplTest  {

	Command cmd;
	TestAsyncCallback callback;
	TestResult result;
	TestAction action;
	ResultHandlerImpl target;
	Injector injector;
	HeavyReport report;
	Commands commandManager;

	@Before
	public void setUp() throws Exception {
		report = mock(HeavyReport.class);
		commandManager = mock(Commands.class);
		injector = Guice.createInjector(new HeavyReportModule(report));
		action = new TestAction("You");
		result = new TestResult("Hi there");
		callback = new TestAsyncCallback();
		cmd = new CommandEntity(action, callback);
		target = new ResultHandlerImpl(result, cmd, injector, commandManager);
	}

        @Test
	public void testCallback() {
		target.callback();
//		Assert.assertEquals("Hi there", callback.getResult().getMessage());
		verify(report).report("Hi there");
		verify(commandManager).delete(cmd);
	}

}
