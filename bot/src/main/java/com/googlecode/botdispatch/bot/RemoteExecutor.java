package com.googlecode.botdispatch.bot;


public interface RemoteExecutor {


    Object[] execute(long callbackId, byte[] serializedResult) throws Exception;
}
