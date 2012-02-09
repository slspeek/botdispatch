package fspotcloud.botdispatch.controller.dispatch;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import junit.framework.TestCase;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.test.TestAction;
import fspotcloud.botdispatch.test.TestResult;
public class ControllerDispatchAsyncTest extends TestCase {

	Commands commandManager;
	TestAction action;
	DefaultControllerDispatchAsync target;
	AsyncCallback<TestResult> callback;

	@Override
	protected void setUp() throws Exception {
		commandManager = mock(Commands.class);
		action = new TestAction("Jim");
		target = new DefaultControllerDispatchAsync(commandManager);
		callback = new AsyncCallback<TestResult> () {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(TestResult result) {
				
				
			}
			
		};
		super.setUp();
	}
	
	public void testDispatch() throws Exception {
		target.execute(action, callback);
		
		verify(commandManager).createAndSave(action, callback);
	}
	
	
	
}
