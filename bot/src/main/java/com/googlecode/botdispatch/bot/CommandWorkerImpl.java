package com.googlecode.botdispatch.bot;

import com.google.inject.assistedinject.Assisted;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import org.apache.commons.lang.SerializationUtils;

import javax.inject.Inject;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommandWorkerImpl implements CommandWorker {

    @SuppressWarnings("unused")
    final static private Logger log = Logger.getLogger(CommandWorkerImpl.class
            .getName());

    final private Dispatch dispatch;
    final private Action<?> action;

    @Inject
    public CommandWorkerImpl(Dispatch dispatch, @Assisted Action<?> action) {
        super();
        this.dispatch = dispatch;
        this.action = action;
    }

    @Override
    public byte[] doExecute() {
        byte[] data = null;
        try {
            Result result = dispatch.execute(action);
            data = SerializationUtils.serialize((Serializable) result);
        } catch (Exception e) {
        	log.log(Level.WARNING, "An exception occured running: " + action, e);
            data = SerializationUtils.serialize(e);
        }
        return data;
    }

}
