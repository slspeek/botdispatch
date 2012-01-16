package fspotcloud.botdispatch.model.command.jpa;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.persist.jpa.JpaPersistModule;

import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.command.CommandManager;

public class CommandModelModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Commands.class).to(CommandManager.class).in(Singleton.class);
		bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(300));
		install(new JpaPersistModule("derby-command"));
	}
}
