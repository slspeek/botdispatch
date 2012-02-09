package fspotcloud.botdispatch.test;

import javax.inject.Inject;

import com.google.guiceberry.GuiceBerryEnvMain;
import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.persist.PersistService;
import fspotcloud.botdispatch.controller.inject.ControllerModule;
import fspotcloud.botdispatch.model.command.jpa.CommandModelModule;

public class BotDispatchGuiceberryEnv extends GuiceBerryModule {

    @Override
    protected void configure() {
        super.configure();
        bind(GuiceBerryEnvMain.class).to(PersistServiceInitMain.class);
        install(new CommandModelModule());
        install(new LocalBotModule());
        install(new ActionsModule());
        install(new ControllerModule());
    }

    static class PersistServiceInitMain implements GuiceBerryEnvMain {

        @Inject
        PersistService service;

        @Override
        public void run() {
            System.setProperty("appengine.orm.duplicate.emf.exception", "true");
            service.start();
            System.out.println("Persistency Started! ");
        }
    }
}
