package fspotcloud.botdispatch.model;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.command.CommandManager;

public class MinimalCommandModelModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Commands.class).to(CommandManager.class).in(Singleton.class);
		bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(300));
	}
}
