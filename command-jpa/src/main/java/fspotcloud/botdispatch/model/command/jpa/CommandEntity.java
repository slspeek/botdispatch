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
public class CommandEntity implements Command {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ctime;
    @Basic
    private boolean locked;
    @Lob
    private Serializable callback;
    @Lob
    private Serializable action;

    public CommandEntity() {
    }

    public CommandEntity(Action<?> action, AsyncCallback<? extends Result> callback) {
        ctime = new Date();
        this.action = (Serializable) action;
        this.callback = (Serializable) callback;
    }

    public Long getId() {
        return id;
    }

    public Date getCtime() {
        return ctime;
    }

    @Override
    public String toString() {
        String result = " : ";
        return result;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Action<?> getAction() {
        return (Action<?>) action;
    }

    public AsyncCallback<? extends Result> getCallback() {
        return (AsyncCallback<? extends Result>) callback;
    }
}
