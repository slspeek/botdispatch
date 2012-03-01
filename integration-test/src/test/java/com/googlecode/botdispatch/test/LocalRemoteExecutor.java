package com.googlecode.botdispatch.test;

import java.io.IOException;

import com.google.inject.Inject;

import com.googlecode.botdispatch.bot.RemoteExecutor;
import com.googlecode.botdispatch.controller.callback.Controller;

public class LocalRemoteExecutor implements RemoteExecutor {

	final private Controller controller;

	@Inject
	public LocalRemoteExecutor(Controller controller) {
		super();
		this.controller = controller;
	}

	@Override
	public Object[] execute(long callbackId, byte[] serializedResult)
			throws Exception {
		Object[] result = null;
		try {
			result = controller.callback(callbackId, serializedResult);
		} catch (IOException e) {
		}
		return result;
	}

}
