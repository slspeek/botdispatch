package com.googlecode.botdispatch.controller.dispatch;

import com.googlecode.botdispatch.NullCallback;
import com.googlecode.botdispatch.SerializableAsyncCallback;
import com.googlecode.botdispatch.model.api.Commands;
import com.googlecode.botdispatch.test.TestAction;
import com.googlecode.botdispatch.test.TestResult;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ControllerDispatchAsyncTest extends TestCase {

    Commands commandManager;
    TestAction action;
    DefaultControllerDispatchAsync target;
    SerializableAsyncCallback<TestResult> callback;

    @Override
    protected void setUp() throws Exception {
        commandManager = mock(Commands.class);
        action = new TestAction("Jim");
        target = new DefaultControllerDispatchAsync(commandManager);
        callback = new NullCallback<TestResult>();
        super.setUp();
    }

    public void testDispatch() throws Exception {
        target.execute(action, callback);

        verify(commandManager).createAndSave(action, callback);
    }


}
