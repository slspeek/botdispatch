package fspotcloud.botdispatch.bot;

import java.util.logging.Logger;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import com.google.inject.Inject;

public class RemoteExecutorImpl implements RemoteExecutor {
	@SuppressWarnings("unused")
	final static private Logger log = Logger.getLogger(RemoteExecutorImpl.class
			.getName());
	final private XmlRpcClient client;

	@Inject
	public RemoteExecutorImpl(XmlRpcClient client) {
		super();
		this.client = client;
	}

	@Override
	public Object[] execute(long callbackId, byte[] serializedResult)
			throws XmlRpcException {
		Object[] args = new Object[] { callbackId, serializedResult };
		Object[] result = (Object[]) client.execute("Controller.callback", args);
		return result;
	}

}
