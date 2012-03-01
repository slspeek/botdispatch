package com.googlecode.botdispatch.bot;


public interface BotDispatchServer {

	void runForever() throws Exception;

	void runForever(int n) throws Exception;

}