package fspotcloud.botdispatch.model.command.jpa;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import org.apache.commons.lang.SerializationUtils;

import com.google.appengine.api.datastore.Blob;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.botdispatch.model.api.Command;

@Entity
public class CommandEntity implements Command {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Action<?> action;
	private AsyncCallback<Result> callback;

	@Basic
	private Blob callbackBlob;

	@Basic
	private Blob actionBlob;

	@Basic
	private Date ctime;

	@Basic
	private boolean locked;

	public CommandEntity(Action<?> action,
			AsyncCallback<Result> callback) throws IOException {
		ctime = new Date();
		this.action = action;
		this.callback = callback;

		callbackBlob = new Blob(
				SerializationUtils.serialize((Serializable) callback));
		actionBlob = new Blob(
				SerializationUtils.serialize((Serializable) action));
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
			action = (Action<?>) SerializationUtils.deserialize(actionBlob.getBytes());
		}
		return action;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AsyncCallback<Result> getCallback() {
		if (callback == null) {
			callback = (AsyncCallback<Result>) SerializationUtils.deserialize(callbackBlob.getBytes());
		}
		return callback;
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
