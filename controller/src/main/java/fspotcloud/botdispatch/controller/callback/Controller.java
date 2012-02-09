package fspotcloud.botdispatch.controller.callback;

import java.io.IOException;
import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import org.apache.commons.lang.SerializationUtils;

import com.google.inject.Inject;

import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.api.NullCommand;

public class Controller {

	final private Commands commandManager;
	final private ResultHandlerFactory handlerFactory;
	final private ErrorHandlerFactory errorHandlerFactory;
	final private ControllerHook hook;

	@Inject
	public Controller(Commands commandManager,
			ResultHandlerFactory handlerFactory,
			ErrorHandlerFactory errorHandlerFactory, ControllerHook hook) {
		super();
		this.hook = hook;
		this.commandManager = commandManager;
		this.handlerFactory = handlerFactory;
		this.errorHandlerFactory = errorHandlerFactory;
	}

	public Object[] callback(long callbackId, byte[] serializedResult)
			throws IOException {
		if (hook != null) {
			hook.preprocess(callbackId, serializedResult);
		}
		Object[] result;
		if (callbackId != -1L) {
			try {
				doCallback(callbackId, serializedResult);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		Command newCommand = commandManager.getAndLockFirstCommand();
		if (newCommand instanceof NullCommand) {
			result = new Object[] { -1L, null };
		} else {
			Action<?> action = newCommand.getAction();
			byte[] actionBytes = SerializationUtils.serialize((Serializable) action);
			result = new Object[] { newCommand.getId(), actionBytes };
		}
		return result;
	}

	private void doCallback(long callbackId, byte[] serializedResult)
			throws IOException, ClassNotFoundException {
		Command callbackCommand = commandManager.getById(callbackId);
		Object result = SerializationUtils.deserialize(serializedResult);
		if (result instanceof Result) {
			ResultHandlerImpl handler = handlerFactory.get((Result) result,
					callbackCommand);
			handler.callback();
		} else if (result instanceof Throwable) {
			ErrorHandlerImpl handler = errorHandlerFactory.get(
					(Throwable) result, callbackCommand);
			handler.onError();
		}

	}

}
