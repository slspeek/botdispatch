package fspotcloud.botdispatch.controller.inject;

import java.util.logging.Logger;

import com.google.inject.servlet.ServletModule;

import fspotcloud.botdispatch.controller.callback.ControllerServlet;

public class ControllerServletModule extends ServletModule {

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(ControllerServletModule.class.getName());

	@Override
	protected void configureServlets() {
		String botSecret = System.getProperty("bot.secret", "SECRET");
		serve("/controller/" + botSecret).with(ControllerServlet.class);
	}

}
