package fspotcloud.botdispatch.controller.inject;

import fspotcloud.botdispatch.controller.callback.ControllerHook;

public class NullControllerHook implements ControllerHook {

	@Override
	public void preprocess(long id, byte[] result) {

	}

}
