package com.googlecode.botdispatch.test;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Result;

public class OtherResult implements Result, Serializable{
	
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
