package com.googlecode.botdispatch.testserver;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import com.googlecode.botdispatch.test.TestResult;

public class TestCallback implements Serializable,
		AsyncCallback<TestResult> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 232324L;
	
	@Inject
	transient List results;

	@Override
	public void onFailure(Throwable caught) {

	}

	@Override
	public void onSuccess(TestResult result) {
		results.add((String)result.getMessage());
	}

}