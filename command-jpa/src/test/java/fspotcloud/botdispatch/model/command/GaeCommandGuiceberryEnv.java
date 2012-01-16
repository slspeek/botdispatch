package fspotcloud.botdispatch.model.command;

import javax.inject.Inject;

import com.google.guiceberry.GuiceBerryEnvMain;
import com.google.guiceberry.GuiceBerryModule;
import com.google.guiceberry.TestWrapper;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

import fspotcloud.botdispatch.model.api.Commands;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GaeCommandGuiceberryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        //bind(TestWrapper.class).to(GaeLocalDatastoreTestWrapper.class);
        bind(GuiceBerryEnvMain.class).to(PersistServiceInitMain.class);
        install(new CommandModelModule());
    }

    static class PersistServiceInitMain implements GuiceBerryEnvMain {

        @Inject
        PersistService service;

        @Override
        public void run() {
            System.setProperty("appengine.orm.duplicate.emf.exception", "true");
            service.start();
            
        }
    }
}

class CommandModelModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Commands.class).to(CommandManager.class).in(Singleton.class);
        bind(Integer.class).annotatedWith(Names.named("maxCommandDelete")).toInstance(new Integer(3));
        install(new JpaPersistModule("derby-command"));
    }
}