package fspotcloud.botdispatch.model;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;

public class ModelModuleTest extends TestCase {

	CommandModelModule module = new CommandModelModule();
	
	public static TestSuite suite() {
		return new TestSuite(ModelModuleTest.class);
	}
	public void testInjector() {
		Injector injector = Guice.createInjector(module);
		assertNotNull(injector);
	}
}
