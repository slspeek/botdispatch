package fspotcloud.botdispatch.bot;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.lang.SerializationUtils;
import org.apache.xmlrpc.XmlRpcException;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.test.ActionsModule;
import fspotcloud.botdispatch.test.TestAction;
import fspotcloud.botdispatch.test.TestResult;
public class BotDispatchServerTest extends TestCase {

	Pauser pauser;
	CommandWorkerFactory factory;
	Injector injector = Guice.createInjector(new BotModule(),
			new ActionsModule());
	RemoteExecutor remote;
	BotDispatchServerImpl target;
	TestAction action = new TestAction("Josie");
	TestResult result = new TestResult("Hello to you, Josie");
	private byte[] serializedAction;
	private byte[] serializedResult;

	protected void setUp() throws Exception {
		pauser = mock(Pauser.class);
		remote = mock(RemoteExecutor.class);
		factory = injector.getInstance(CommandWorkerFactory.class);
		serializedAction = SerializationUtils.serialize(action);
		serializedResult = SerializationUtils.serialize(result); 
		target = new BotDispatchServerImpl(remote, factory, pauser, 10000);
		super.setUp();
	}

	public void testEmptyStart() throws Exception {
		when(remote.execute(-1L,null)).thenReturn(new Object[] { -1L, null});
		target.runForever(1);
		verify(pauser).pause();
	}
	
	public void testStart() throws Exception {
		when(remote.execute(-1L,null)).thenReturn(new Object[] { 1000L, serializedAction});
		when(remote.execute(1000L,serializedResult)).thenReturn(new Object[] { -1L, null});
		target.runForever(2);
		verify(remote).execute(1000L, serializedResult);
		verify(pauser).pause();
	}
}
