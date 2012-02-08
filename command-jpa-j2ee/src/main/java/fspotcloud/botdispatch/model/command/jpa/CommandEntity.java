package fspotcloud.botdispatch.model.command.jpa;

import com.google.gwt.user.client.rpc.AsyncCallback;
import fspotcloud.botdispatch.model.api.Command;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class CommandEntity extends CommandEntityBase {

    @Lob
    private Serializable callback;
    @Lob
    private Serializable action;

    public CommandEntity() {
    }

    public CommandEntity(Action<?> action, AsyncCallback<? extends Result> callback) {
        this.action = (Serializable) action;
        this.callback = (Serializable) callback;
    }

    public Action<?> getAction() {
        return (Action<?>) action;
    }

    public AsyncCallback<? extends Result> getCallback() {
        return (AsyncCallback<? extends Result>) callback;
    }

    public void setAction(Action<?> action) {
        this.action = (Serializable) action;
    }

    public void setCallback(AsyncCallback<? extends Result> callback) {
        this.callback = (Serializable) callback;

    }
}
