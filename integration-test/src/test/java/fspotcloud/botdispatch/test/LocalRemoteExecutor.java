package fspotcloud.botdispatch.test;

import java.io.IOException;

import org.apache.xmlrpc.XmlRpcException;

import com.google.inject.Inject;

import fspotcloud.botdispatch.bot.RemoteExecutor;
import fspotcloud.botdispatch.controller.callback.Controller;

public class LocalRemoteExecutor implements RemoteExecutor {

	final private Controller controller;

	@Inject
	public LocalRemoteExecutor(Controller controller) {
		super();
		this.controller = controller;
	}

	@Override
	public Object[] execute(long callbackId, byte[] serializedResult)
			throws XmlRpcException {
		// TODO Auto-generated method stub
		Object[] result = null;
		try {
			result = controller.callback(callbackId, serializedResult);
		} catch (IOException e) {
			// TODO: handle exception
		}
		return result;
	}

}
