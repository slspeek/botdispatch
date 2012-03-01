package com.googlecode.botdispatch.test;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;

import com.googlecode.botdispatch.bot.BotDispatchServer;
import com.googlecode.botdispatch.bot.BotDispatchServerImpl;
import com.googlecode.botdispatch.bot.CommandWorker;
import com.googlecode.botdispatch.bot.CommandWorkerFactory;
import com.googlecode.botdispatch.bot.CommandWorkerImpl;
import com.googlecode.botdispatch.bot.Pauser;
import com.googlecode.botdispatch.bot.PauserImpl;
import com.googlecode.botdispatch.bot.RemoteExecutor;

public class LocalBotModule extends AbstractModule {

	protected void configure() {
		bind(BotDispatchServer.class).to(BotDispatchServerImpl.class);
		bind(Pauser.class).to(PauserImpl.class);
		bind(RemoteExecutor.class).to(LocalRemoteExecutor.class);
		bind(Integer.class).annotatedWith(Names.named("pause")).toInstance(
				Integer.valueOf(System.getProperty("pause", "0")));
		install(new FactoryModuleBuilder().implement(CommandWorker.class,
				CommandWorkerImpl.class).build(CommandWorkerFactory.class));
	}
	
}
