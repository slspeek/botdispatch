package fspotcloud.botdispatch.test;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

public class ThrowingActionHandler extends
		SimpleActionHandler<ThrowingAction, TestResult> {

	@Override
	public TestResult execute(ThrowingAction action, ExecutionContext context)
			throws DispatchException {
		String message = action.getName().toUpperCase();
		if (action.getName().equals("Demian")) {
			throw new ActionException("Demian is not allowed");
		}
		TestResult result = new TestResult(message);
		return result;
	}

}
