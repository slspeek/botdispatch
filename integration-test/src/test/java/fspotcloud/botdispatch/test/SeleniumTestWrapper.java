package fspotcloud.botdispatch.test;

import javax.inject.Inject;

import com.google.common.testing.TearDown;
import com.google.common.testing.TearDownAccepter;
import com.google.guiceberry.TestWrapper;
import com.thoughtworks.selenium.Selenium;

public class SeleniumTestWrapper implements TestWrapper {

	@Inject
	Selenium selenium;
	@Inject TearDownAccepter tearDownAccepter;
	
	public void toRunBeforeTest() {
		tearDownAccepter.addTearDown(new TearDown() {

			public void tearDown() throws Exception {
				selenium.close();
			}
		});
	}
}
