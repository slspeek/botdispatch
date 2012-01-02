package fspotcloud.botdispatch.test;


public interface HeavyReport {
	void report(String message);
	void error(Throwable error);
}
