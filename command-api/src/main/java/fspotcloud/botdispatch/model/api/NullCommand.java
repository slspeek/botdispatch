package fspotcloud.botdispatch.model.api;

import java.util.Date;


import fspotcloud.botdispatch.model.api.Command;
import java.io.Serializable;

public class NullCommand implements Command {

    public static Command INSTANCE = new NullCommand();

    @Override
    public Long getId() {
        return -1L;
    }

    @Override
    public Serializable getAction() {
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
    public Serializable getCallback() {
        return null;
    }
}
