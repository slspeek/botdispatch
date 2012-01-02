package fspotcloud.botdispatch.test;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;

public class TestActionHandler extends
		SimpleActionHandler<TestAction, TestResult> {

	@Override
	public TestResult execute(TestAction action, ExecutionContext context)
			throws DispatchException {
		String message = "Hello to you, " + action.getName();
		TestResult result = new TestResult(message);
		return result;
	}

}
