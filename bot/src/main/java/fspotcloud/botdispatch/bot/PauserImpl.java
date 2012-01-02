package fspotcloud.botdispatch.bot;

import javax.inject.Inject;

import com.google.inject.name.Named;

public class PauserImpl implements Pauser {
	
	private int idleCount = 0;
	final private int maxSeconds;
	
	@Inject
	public PauserImpl(@Named("pause") int maxSeconds) {
		super();
		this.maxSeconds = maxSeconds;
	}

	public int pause() {
		int seconds = getPauseSeconds();
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
		}
		return seconds;
	}

	@Override
	public void resetIdleCount() {
		this.idleCount = 0;
	}

	@Override
	public void increaseIdleCount() {
		idleCount++;
	}
	
	@Override
	public int getPauseSeconds() {
		return (int) Math.min(Math.pow(2, idleCount), maxSeconds);
	}
	
}
