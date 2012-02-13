package fspotcloud.botdispatch.model.jpa.gae.command;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.simplejpadao.EMProvider;
import javax.persistence.EntityManager;

public class CommandModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Commands.class).to(CommandManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(300));
        bind(PersistServiceInitializer.class);
        bind(EntityManager.class).toProvider(EMProvider.class);
        bind(String.class).annotatedWith(Names.named("persistence-unit")).toInstance("gae-jpa-command");
    }
}
