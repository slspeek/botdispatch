package fspotcloud.botdispatch.model.api;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.io.Serializable;
import java.util.Date;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public interface Command {

    Long getId();

    Action<?> getAction();

    AsyncCallback<? extends Result> getCallback();

    Date getCtime();

    boolean isLocked();

    void setLocked(boolean b);
}