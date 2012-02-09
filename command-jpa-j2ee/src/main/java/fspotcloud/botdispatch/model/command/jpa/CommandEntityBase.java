package fspotcloud.botdispatch.model.command.jpa;

import fspotcloud.botdispatch.model.api.Command;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public abstract class CommandEntityBase implements Command {

    @Id
    @GeneratedValue(generator="increment")
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //Derby with Hibernate do not want this, I know
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ctime;
    @Basic
    private boolean locked;

    public CommandEntityBase() {
        ctime = new Date();
    }

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
}
