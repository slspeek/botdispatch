package com.googlecode.botdispatch.bot;

import com.google.inject.Inject;
import net.customware.gwt.dispatch.shared.Action;
import org.apache.commons.lang.SerializationUtils;

import java.util.logging.Logger;

public class BotDispatchServerImpl implements BotDispatchServer {

    final static private Logger log = Logger
            .getLogger(BotDispatchServerImpl.class.getName());

    final private RemoteExecutor remote;
    final private CommandWorkerFactory factory;
    final private Pauser pauser;

    @Inject
    public BotDispatchServerImpl(RemoteExecutor remote,
                                 CommandWorkerFactory factory, Pauser pauser) {
        super();
        this.factory = factory;
        this.remote = remote;
        this.pauser = pauser;
    }

    @Override
    public void runForever() throws Exception {
        runForever(Integer.MAX_VALUE);
    }

    public void runForever(int n) throws Exception {
        byte[] serializedResult = null;
        long callbackId = -1;
        Action<?> currentAction;
        for (int i = 0; i < n; i++) {
            log.info("Posting the result for: " + callbackId);
            Object[] result = remote.execute(callbackId, serializedResult);
            callbackId = (Long) result[0];
            if (callbackId != -1) {
                pauser.resetIdleCount();
                byte[] serializedAction = (byte[]) result[1];
                currentAction = (Action<?>) SerializationUtils
                        .deserialize(serializedAction);
            } else {
                currentAction = null;
            }

            if (currentAction == null) {
                pauser.increaseIdleCount();
                log.info("No action at this time, sleeping for " + pauser.getPauseSeconds()
                        + "s.");
                pauser.pause();
                ;
            } else {
                CommandWorker worker = factory.get(currentAction);
                serializedResult = worker.doExecute();
            }

        }
    }
}
