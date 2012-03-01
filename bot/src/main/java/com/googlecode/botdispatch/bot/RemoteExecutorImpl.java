package com.googlecode.botdispatch.bot;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.inject.Named;

import org.apache.commons.lang.SerializationUtils;

import com.google.inject.Inject;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

public class RemoteExecutorImpl implements RemoteExecutor {
	@SuppressWarnings("unused")
	final static private Logger log = Logger.getLogger(RemoteExecutorImpl.class
			.getName());
	String host;

	@Inject
	public RemoteExecutorImpl(@Named("endpoint") String host) {
		this.host = host;
	}

	@Override
	public Object[] execute(long callbackId, byte[] serializedResult)
			throws Exception {
		Object[] args = new Object[] { callbackId, serializedResult };
		byte[] payload = SerializationUtils.serialize(args);
		
		WebConversation webconv = new WebConversation();
		ByteArrayInputStream in = new ByteArrayInputStream(payload);
		PostMethodWebRequest request = new PostMethodWebRequest(host, in, "image/jpeg");
		WebResponse response = webconv.getResponse(request);


		InputStream inStream = response.getInputStream();
		// Get Response
		// - For debugging purposes only!
		// Close I/O streams
		byte[] returnedPayload = getPayload(inStream);
		
		inStream.close();

		Object[] result = (Object[]) SerializationUtils.deserialize(returnedPayload);
		return result;
	}
	private byte[] getPayload(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BufferedInputStream buff = new BufferedInputStream(in);
		byte[] buf = new byte[4 * 1024];
		int len;
		while ((len = buff.read(buf, 0, buf.length)) != -1) {
			out.write(buf, 0, len);
		}
		in.close();
		buff.close();
		out.close();
		return out.toByteArray();
	}

}
