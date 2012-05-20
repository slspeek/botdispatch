package com.googlecode.botdispatch.test;

import com.google.inject.Inject;
import com.googlecode.botdispatch.SerializableAsyncCallback;

import java.util.Random;
import java.util.logging.Logger;

public class TestAsyncCallback implements SerializableAsyncCallback<TestResult> {

    private static final long serialVersionUID = 23234243L;
    private static final Logger log = Logger.getLogger(TestAsyncCallback.class.getName());
    @Inject
    transient HeavyReport report;
    TestResult result;
    private Throwable error;
    private byte[] bigFile;

    static private Random random = new Random();

    public TestAsyncCallback() {
        byte[] randBytes = new byte[100000];
        random.nextBytes(randBytes);
        this.bigFile = randBytes;
    }

    @Override
    public void onFailure(Throwable caught) {
        this.error = caught;
        report.error(error);
    }

    @Override
    public void onSuccess(TestResult result) {
        this.result = result;
        report.report(result.getMessage());
    }

    public TestResult getResult() {
        return result;
    }

    public Throwable getError() {
        return error;
    }
}
