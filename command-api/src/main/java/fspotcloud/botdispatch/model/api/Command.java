package fspotcloud.botdispatch.model.api;

import java.io.Serializable;
import java.util.Date;

public interface Command {

    Long getId();

    Serializable getAction();

    Serializable getCallback();

    Date getCtime();

    boolean isLocked();

    void setLocked(boolean b);
}