package com.googlecode.botdispatch.bot;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.botdispatch.test.ActionsModule;
import com.googlecode.botdispatch.test.TestAction;
import com.googlecode.botdispatch.test.TestResult;
import junit.framework.TestCase;
import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.Action;
import org.apache.commons.lang.SerializationUtils;

public class CommandWorkerImplTest extends TestCase {

    private Dispatch dispatch;

    private Action<?> action;
    Injector injector;

    CommandWorkerImpl target;

    @Override
    protected void setUp() throws Exception {
        action = new TestAction("Richard");
        injector = Guice.createInjector(new ActionsModule());
        dispatch = injector.getInstance(Dispatch.class);
        target = new CommandWorkerImpl(dispatch, action);
        super.setUp();
    }

    public void testRun() throws Exception,
            ClassNotFoundException {
        byte[] resultInBytes = target.doExecute();
        TestResult testResult = (TestResult) SerializationUtils.deserialize(resultInBytes);
        assertEquals("Hello to you, Richard", testResult.getMessage());
    }

}
