package com.googlecode.botdispatch.test;

import com.google.gwt.user.client.rpc.IsSerializable;
import net.customware.gwt.dispatch.shared.Result;

import java.io.Serializable;

public class TestResult implements Result, IsSerializable, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2320328423L;
    private String message;

    TestResult() {
    }

    public TestResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
