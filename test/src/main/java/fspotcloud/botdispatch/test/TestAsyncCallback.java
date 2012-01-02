package fspotcloud.botdispatch.test;

import java.io.Serializable;
import java.util.logging.Logger;

import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class TestAsyncCallback implements AsyncCallback<TestResult>, Serializable {

	private static final long serialVersionUID = 23234243L;
	private static final Logger log = Logger.getLogger(TestAsyncCallback.class
			.getName());
	
	
	@Inject transient HeavyReport report;
	
	TestResult result;
	private Throwable error;
	
	public TestAsyncCallback() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onFailure(Throwable caught) {
		this.error =  caught;
		report.error(error);
	}

	@Override
	public void onSuccess(TestResult result) {
		this.result = result;
		report.report(result.getMessage());
	}

	public TestResult getResult() {
		return result;
	}
	public Throwable getError() {
		return error;
	}
}
