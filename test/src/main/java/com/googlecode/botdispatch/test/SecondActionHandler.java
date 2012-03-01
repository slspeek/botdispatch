package com.googlecode.botdispatch.test;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;

public class SecondActionHandler extends
		SimpleActionHandler<SecondAction, TestResult> {

	@Override
	public TestResult execute(SecondAction action, ExecutionContext context)
			throws DispatchException {
		String message = action.getName().toUpperCase();
		TestResult result = new TestResult(message);
		return result;
	}

}
