package com.googlecode.botdispatch.test;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;

public class TestAction implements Action<TestResult>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 31253859248534756L;

	private String name;

	public TestAction() {
	}

	public TestAction(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean equals(Object other) {
		if (other instanceof TestAction) {
			return ((TestAction) other).getName().equals(name);

		} else {
			return false;
		}
	}

}
