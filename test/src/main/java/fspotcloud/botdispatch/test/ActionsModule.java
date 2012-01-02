package fspotcloud.botdispatch.test;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

public class ActionsModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(TestAction.class, TestActionHandler.class);
		bindHandler(SecondAction.class, SecondActionHandler.class);
		bindHandler(ThrowingAction.class, ThrowingActionHandler.class);
	}

}