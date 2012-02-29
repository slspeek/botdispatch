package fspotcloud.botdispatch.model.command.jpa;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.command.CommandManager;
import fspotcloud.simplejpadao.EMProvider;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CommandModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Commands.class).to(CommandManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(300));
        bind(EntityManager.class).toProvider(EMProvider.class);
         EntityManagerFactory factory = Persistence.createEntityManagerFactory("derby-command");
        System.out.println("EMF " + factory);
        bind(EntityManagerFactory.class).toInstance(factory);

    }
}
