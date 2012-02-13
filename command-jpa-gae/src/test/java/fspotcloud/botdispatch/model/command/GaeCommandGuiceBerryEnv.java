package fspotcloud.botdispatch.model.command;

import fspotcloud.botdispatch.model.jpa.gae.command.CommandManager;
import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestWrapper;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.simplejpadao.EMProvider;
import javax.persistence.EntityManager;

public class GaeCommandGuiceBerryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        bind(TestWrapper.class).to(GaeLocalDatastoreTestWrapper.class);
        install(new CommandModelModule());
    }
}

class CommandModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(3));
        bind(EntityManager.class).toProvider(EMProvider.class);
        bind(String.class).annotatedWith(Names.named("persistence-unit")).toInstance("gae-jpa-command");
        bind(Commands.class).to(CommandManager.class).in(Singleton.class);
    }
}