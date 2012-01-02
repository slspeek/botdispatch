package fspotcloud.botdispatch.controller.callback;

import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.xmlrpc.webserver.XmlRpcServlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class GuiceXmlRpcServlet extends XmlRpcServlet {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(GuiceXmlRpcServlet.class
			.getName());

	@Inject
	private GuiceRequestProcessorFactoryFactory guiceFactoryFactory;

	@Override
	public void init(ServletConfig pConfig) throws ServletException {
		setRequestProcessorFactoryFactory(guiceFactoryFactory);
		super.init(pConfig);
	}

}
