package fspotcloud.botdispatch.model.command;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.simplejpadao.EMProvider;
import fspotcloud.simplejpadao.SimpleDAOGenId;
import javax.persistence.EntityManager;

public class J2eeCommandGuiceBerryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        install(new CommandModelModule());
    }
}

class CommandModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Commands.class).to(CommandManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(3));
        bind(String.class).annotatedWith(Names.named("persistence-unit")).toInstance("derby-command");
        bind(EntityManager.class).toProvider(EMProvider.class);
        bind(SimpleDAOGenId.class).to(CommandManager.class);
    }
}