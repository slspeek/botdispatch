package com.googlecode.botdispatch.testserver;

import java.util.List;

import com.google.inject.Inject;
import com.googlecode.botdispatch.SerializableAsyncCallback;

import com.googlecode.botdispatch.test.TestResult;

public class TestCallback implements 
		SerializableAsyncCallback<TestResult> {

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