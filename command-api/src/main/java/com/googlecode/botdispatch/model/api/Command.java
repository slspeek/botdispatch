package com.googlecode.botdispatch.model.api;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.simplejpadao.HasKey;
import java.util.Date;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public interface Command extends HasKey<Long> {

    Long getId();

    Action<?> getAction();
    
//    void setAction(Action<?> action);

    AsyncCallback<? extends Result> getCallback();
    
//    void setCallback(AsyncCallback<? extends Result> callback);

    Date getCtime();

    boolean isLocked();

    void setLocked(boolean b);
}