package fspotcloud.botdispatch.bot;

import java.io.IOException;

import org.apache.xmlrpc.XmlRpcException;

public interface BotDispatchServer {

	void runForever() throws XmlRpcException, IOException,
			ClassNotFoundException;

	void runForever(int n) throws XmlRpcException, IOException,
			ClassNotFoundException;

}