package fspotcloud.botdispatch.bot;

import junit.framework.TestCase;

public class PauserImplTest extends TestCase {

	Pauser pauser = new PauserImpl(4);
	
	public void testPause2Seconds(){
		long before = System.currentTimeMillis();
		pauser.pause();
		long after = System.currentTimeMillis();
		assertTrue(after - before > 999);
	}
	
	public void testCalculatePause() {
		pauser = new PauserImpl(4);
		pauser.increaseIdleCount();
		assertEquals(2, pauser.getPauseSeconds());
		pauser.increaseIdleCount();
		assertEquals(4, pauser.getPauseSeconds());
		pauser.resetIdleCount();
		pauser.increaseIdleCount();
		assertEquals(2, pauser.getPauseSeconds());
		
		pauser = new PauserImpl(1);
		pauser.increaseIdleCount();
		assertEquals(1, pauser.getPauseSeconds());
		pauser.increaseIdleCount();
		assertEquals(1, pauser.getPauseSeconds());
	}
	
}
