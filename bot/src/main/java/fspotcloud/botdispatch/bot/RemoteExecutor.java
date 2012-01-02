package fspotcloud.botdispatch.bot;

import org.apache.xmlrpc.XmlRpcException;

public interface RemoteExecutor {
	
	
	Object[] execute(long callbackId, byte[] serializedResult) throws XmlRpcException;
}
