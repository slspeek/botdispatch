package fspotcloud.botdispatch.model.command.jpa;

import fspotcloud.botdispatch.model.api.Command;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.apache.commons.lang.SerializationUtils;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class CommandEntity implements Command {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    //transient private Blob callbackBlob;
    //transient private Blob actionBlob;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ctime;
    @Basic
    private boolean locked;
    @Column(columnDefinition = "BLOB")
    private Serializable callback;
    @Column(columnDefinition = "BLOB")
    private Serializable action;

    public CommandEntity() {
    }

    public CommandEntity(Serializable action, Serializable callback) {
        ctime = new Date();
        this.action = action;
        this.callback = callback;
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

    public Serializable getAction() {
        return action;
    }

    public Serializable getCallback() {
        return callback;
    }
}
