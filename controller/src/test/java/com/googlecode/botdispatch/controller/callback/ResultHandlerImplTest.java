package com.googlecode.botdispatch.controller.callback;

import com.googlecode.botdispatch.controller.callback.ResultHandlerImpl;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.googlecode.botdispatch.model.api.Command;
import com.googlecode.botdispatch.model.api.Commands;
import com.googlecode.botdispatch.model.jpa.gae.command.CommandEntity;
import com.googlecode.botdispatch.test.HeavyReport;
import com.googlecode.botdispatch.test.HeavyReportModule;
import com.googlecode.botdispatch.test.TestAction;
import com.googlecode.botdispatch.test.TestAsyncCallback;
import com.googlecode.botdispatch.test.TestResult;
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
