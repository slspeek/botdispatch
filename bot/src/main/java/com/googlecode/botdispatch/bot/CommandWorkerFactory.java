package com.googlecode.botdispatch.bot;

import net.customware.gwt.dispatch.shared.Action;

public interface CommandWorkerFactory {
	CommandWorker get(Action<?> action);
}
