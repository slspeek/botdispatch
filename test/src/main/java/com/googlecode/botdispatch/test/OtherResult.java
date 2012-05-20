package com.googlecode.botdispatch.test;

import net.customware.gwt.dispatch.shared.Result;

import java.io.Serializable;

public class OtherResult implements Result, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2320423L;
    private String message;

    OtherResult() {
    }

    public OtherResult(String message) {
        this.message = message;
    }

    public String getText() {
        return message;
    }

}
