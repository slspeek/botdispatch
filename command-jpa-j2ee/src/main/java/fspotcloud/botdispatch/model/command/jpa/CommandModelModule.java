package fspotcloud.botdispatch.model.command.jpa;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.command.CommandManager;
import fspotcloud.simplejpadao.EMProvider;
import javax.persistence.EntityManager;

public class CommandModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Commands.class).to(CommandManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(300));
        bind(EntityManager.class).toProvider(EMProvider.class);
        bind(String.class).annotatedWith(Names.named("persistence-unit")).toInstance("derby-command");
    }
}
