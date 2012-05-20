package com.googlecode.botdispatch.bot;

import com.googlecode.botdispatch.test.TestAsyncCallback;
import junit.framework.TestCase;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;

public class AsyncSerializationTest extends TestCase {


    public void testOne() throws IOException, ClassNotFoundException {
        TestAsyncCallback callback = new TestAsyncCallback();
        byte[] serializedResult = SerializationUtils.serialize(callback);

        TestAsyncCallback back = (TestAsyncCallback) SerializationUtils.deserialize(serializedResult);
        assertNotNull(back);
    }
}
