package fspotcloud.botdispatch.bot;

public interface Pauser {
	
	int pause();
	
	void resetIdleCount();
	
	void increaseIdleCount();

	int getPauseSeconds();
}