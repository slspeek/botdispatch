package com.googlecode.botdispatch.bot;

import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;

public class BotModule extends AbstractModule {

	protected void configure() {
		bind(BotDispatchServer.class).to(BotDispatchServerImpl.class);
		bind(Pauser.class).to(PauserImpl.class);
		bind(RemoteExecutor.class).to(RemoteExecutorImpl.class).in(
				Singleton.class);
		bind(String.class).annotatedWith(Names.named("endpoint")).toInstance(
				"http://" + System.getProperty("endpoint", "localhost:8080")
						+ "/controller/"
						+ System.getProperty("bot.secret", "SECRET"));
		bind(Integer.class).annotatedWith(Names.named("pause")).toInstance(
				Integer.valueOf(System.getProperty("pause", "10")));
		install(new FactoryModuleBuilder().implement(CommandWorker.class,
				CommandWorkerImpl.class).build(CommandWorkerFactory.class));
	}

}
