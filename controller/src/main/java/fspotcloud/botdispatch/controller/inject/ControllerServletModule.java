package fspotcloud.botdispatch.controller.inject;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.inject.servlet.ServletModule;

import fspotcloud.botdispatch.controller.callback.GuiceXmlRpcServlet;

public class ControllerServletModule extends ServletModule {

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(ControllerServletModule.class.getName());

	@Override
	protected void configureServlets() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("enabledForExtensions", "true");
		String botSecret = System.getProperty("bot.secret");
		serve("/xmlrpc/" + botSecret).with(GuiceXmlRpcServlet.class, params);
	}

}
