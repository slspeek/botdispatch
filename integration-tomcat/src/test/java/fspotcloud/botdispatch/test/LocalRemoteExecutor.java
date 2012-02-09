package fspotcloud.botdispatch.test;

import java.io.IOException;

import com.google.inject.Inject;

import fspotcloud.botdispatch.bot.RemoteExecutor;
import fspotcloud.botdispatch.controller.callback.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalRemoteExecutor implements RemoteExecutor {

    final private Controller controller;

    @Inject
    public LocalRemoteExecutor(Controller controller) {
        super();
        this.controller = controller;
    }

    @Override
    public Object[] execute(long callbackId, byte[] serializedResult) {
        Object[] result = null;
        try {
            result = controller.callback(callbackId, serializedResult);
        } catch (IOException ex) {
            Logger.getLogger(LocalRemoteExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
