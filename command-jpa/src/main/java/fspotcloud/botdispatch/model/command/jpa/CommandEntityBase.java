package fspotcloud.botdispatch.model.command.jpa;

import fspotcloud.botdispatch.model.api.Command;
import java.util.Date;
import javax.persistence.*;

@Entity
public abstract class CommandEntityBase implements Command {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ctime;
    @Basic
    private boolean locked;

    public CommandEntityBase() {
        ctime = new Date();
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
}
