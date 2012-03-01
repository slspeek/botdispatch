package com.googlecode.botdispatch.test;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;

public class SecondAction implements Action<TestResult>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 31259248534756L;

	private String name;

	public SecondAction() {
	}

	public SecondAction(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean equals(Object other) {
		if (other instanceof SecondAction) {
			return ((SecondAction) other).getName().equals(name);

		} else {
			return false;
		}
	}

}
