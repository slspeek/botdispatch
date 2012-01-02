package fspotcloud.botdispatch.model;

import javax.jdo.PersistenceManager;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PersistenceManagerProviderTest extends TestCase {

	public static TestSuite suite(){
		return new TestSuite(PersistenceManagerProviderTest.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testPersistenceManagerProvider() {
		//Test a simple instantiation
		PersistenceManagerProvider pmp = new PersistenceManagerProvider();
	}

	public final void testGet() {
		PersistenceManagerProvider pmp = new PersistenceManagerProvider();
		PersistenceManager pm = pmp.get();
		assertNotNull(pm);
	}

}
