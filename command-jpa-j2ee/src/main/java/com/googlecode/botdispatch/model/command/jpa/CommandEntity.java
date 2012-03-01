package com.googlecode.botdispatch.model.command.jpa;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.botdispatch.model.api.Command;
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
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //Derby with Hibernate do not want this, I know
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ctime;
    @Basic
    private boolean locked;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Date getCtime() {
        return ctime;
    }

    @Override
    public String toString() {
        String result = " : ";
        return result;
    }

    @Override
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }
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
