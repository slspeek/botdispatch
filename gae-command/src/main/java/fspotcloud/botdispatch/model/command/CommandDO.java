package fspotcloud.botdispatch.model.command;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import org.apache.commons.lang.SerializationUtils;

import com.google.appengine.api.datastore.Blob;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.botdispatch.model.api.Command;

@PersistenceCapable
public class CommandDO implements Command {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent(serialized = "true")
	private Object action;

	@Persistent(serialized = "true")
	private Object callback;

	@Persistent 
	private Blob callbackBlob;
	
	@Persistent 
	private Blob actionBlob;
	
	@Persistent
	private Date ctime;

	@Persistent
	private boolean locked;
	
	public CommandDO(Action<?> action, AsyncCallback<? extends Result> callback) throws IOException {
		ctime = new Date();
		this.action = action;
		this.callback = callback;
		
		callbackBlob = new Blob(SerializationUtils.serialize((Serializable)callback));
		actionBlob = new Blob(SerializationUtils.serialize((Serializable)action));
	}

	public Long getId() {
		return id;
	}

	public Date getCtime() {
		return ctime;
	}

	@Override
	public Action<?> getAction() {
		if (action == null) {
			action = SerializationUtils.deserialize(actionBlob.getBytes());
		}
		return (Action<?>) action;
	}

	@Override
	public AsyncCallback<Result> getCallback() {
		if (callback == null) {
			callback = SerializationUtils.deserialize(callbackBlob.getBytes());
		}
		return (AsyncCallback<Result>) callback;
	}

	@Override
	public String toString() {
		String result = String.valueOf(action) + " : "
				+ String.valueOf(callback);
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
