package fspotcloud.botdispatch.controller.callback;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SerializationUtils;

import com.google.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class ControllerServlet extends HttpServlet {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(ControllerServlet.class
			.getName());

	@Inject
	private Controller controller;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		byte[] payload = getPayload(req);
		Object[] resultArray = (Object[]) SerializationUtils
				.deserialize(payload);
		Object[] newAssignment = controller.callback((Long) resultArray[0],
				(byte[]) resultArray[1]);
		byte[] returnPayload = SerializationUtils.serialize(newAssignment);
		resp.setContentType("image/jpeg");
		ServletOutputStream outputStream = resp.getOutputStream();
		outputStream.write(returnPayload);
		outputStream.close();
		resp.flushBuffer();
	}

	private byte[] getPayload(ServletRequest request) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = request.getInputStream();
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
