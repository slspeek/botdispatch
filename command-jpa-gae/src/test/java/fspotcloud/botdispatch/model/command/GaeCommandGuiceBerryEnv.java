package fspotcloud.botdispatch.model.command;

import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestWrapper;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.jpa.gae.command.CommandManager;
import fspotcloud.simplejpadao.EMProvider;
import fspotcloud.simplejpadao.SimpleDAOGenId;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
         EntityManagerFactory factory = Persistence.createEntityManagerFactory("gae-jpa-command");
        System.out.println("EMF " + factory);
        bind(EntityManagerFactory.class).toInstance(factory);

        bind(Commands.class).to(CommandManager.class).in(Singleton.class);
        bind(SimpleDAOGenId.class).to(CommandManager.class);
    }
}