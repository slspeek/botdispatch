package com.googlecode.botdispatch.test;

import net.customware.gwt.dispatch.shared.Action;

import java.io.Serializable;

public class ThrowingAction implements Action<TestResult>, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3129248534756L;

    private String name;

    public ThrowingAction() {
    }

    public ThrowingAction(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equals(Object other) {
        if (other instanceof ThrowingAction) {
            return ((ThrowingAction) other).getName().equals(name);

        } else {
            return false;
        }
    }

}
