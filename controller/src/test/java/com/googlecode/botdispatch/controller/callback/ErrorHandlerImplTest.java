package com.googlecode.botdispatch.controller.callback;

import com.googlecode.botdispatch.controller.callback.ErrorHandlerImpl;
import static org.mockito.Mockito.*;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.googlecode.botdispatch.model.api.Command;
import com.googlecode.botdispatch.model.jpa.gae.command.CommandEntity;
import com.googlecode.botdispatch.test.HeavyReport;
import com.googlecode.botdispatch.test.HeavyReportModule;
import com.googlecode.botdispatch.test.TestAsyncCallback;
import com.googlecode.botdispatch.test.ThrowingAction;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

        

public class ErrorHandlerImplTest   {

	Command cmd;
	TestAsyncCallback callback;
	Throwable caught;
	ThrowingAction action;
	ErrorHandlerImpl target;
	Injector injector;
	HeavyReport report;

	@Before
	public void setUp() throws Exception {
		report = mock(HeavyReport.class);
		injector = Guice.createInjector(new HeavyReportModule(report));
		action = new ThrowingAction("Demian");
		caught = new Throwable();
		callback = new TestAsyncCallback();
		cmd = new CommandEntity(action, callback);
		target = new ErrorHandlerImpl(caught, cmd, injector);
	}

        @Test
	public void testOnError() {
		target.onError();
		verify(report).error(caught);
	}

}
