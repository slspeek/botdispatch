package fspotcloud.botdispatch.controller.callback;

import java.util.logging.Logger;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class GuiceRequestProcessorFactoryFactory implements
		RequestProcessorFactoryFactory {

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(GuiceRequestProcessorFactoryFactory.class.getName());

	@Inject
	private Injector injector;

	@Inject
	public GuiceRequestProcessorFactoryFactory(Injector injector) {
		this.injector = injector;
	}

	@Override
	public RequestProcessorFactory getRequestProcessorFactory(final Class pClass)
			throws XmlRpcException {
		return new RequestProcessorFactory() {
			@SuppressWarnings("unchecked")
			@Override
			public Object getRequestProcessor(XmlRpcRequest pRequest)
					throws XmlRpcException {
				return injector.getInstance(pClass);
			}
		};
	}
}
