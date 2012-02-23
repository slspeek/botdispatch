package fspotcloud.botdispatch.model.api;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Date;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public class NullCommand implements Command {

    public static Command INSTANCE = new NullCommand();

    @Override
    public Long getId() {
        return -1L;
    }

    @Override
    public Action<?> getAction() {
        return null;
    }

    @Override
    public Date getCtime() {
        return null;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public void setLocked(boolean b) {
    }

    @Override
    public AsyncCallback<? extends Result> getCallback() {
        return null;
    }

//    @Override
//    public void setAction(Action<?> action) {
//    }
//
//    @Override
//    public void setCallback(AsyncCallback<? extends Result> callback) {
//    }
}
