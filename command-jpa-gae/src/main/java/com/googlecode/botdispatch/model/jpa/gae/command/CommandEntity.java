package com.googlecode.botdispatch.model.jpa.gae.command;

import com.google.appengine.api.datastore.Blob;
import com.googlecode.botdispatch.AsyncCallback;
import com.googlecode.botdispatch.model.api.Command;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;
import org.apache.commons.lang.SerializationUtils;

@Entity
public class CommandEntity implements Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    //@Lob
    private Blob callback;
    //@Lob
    private Blob action;

    public CommandEntity() {
    }

    public CommandEntity(Action<?> action, AsyncCallback<? extends Result> callback) {
        ctime = new Date();
        setAction(action);
        setCallback(callback);
    }

    public Action<?> getAction() {
        return (Action<?>) SerializationUtils.deserialize(action.getBytes());
    }

    public AsyncCallback<? extends Result> getCallback() {
        return (AsyncCallback<? extends Result>) SerializationUtils.deserialize(callback.getBytes());
    }

    private void setAction(Action<?> action) {
        this.action = new Blob(SerializationUtils.serialize((Serializable) action));
    }

    private void setCallback(AsyncCallback<? extends Result> callback) {
        this.callback = new Blob(SerializationUtils.serialize((Serializable) callback));
    }

    public void setId(long id) {
        this.id = id;
    }
}
