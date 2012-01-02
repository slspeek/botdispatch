package fspotcloud.botdispatch.model;

import javax.jdo.PersistenceManager;

public class CommandModelModule extends MinimalCommandModelModule {

	@Override
	protected void configure() {
		super.configure();
		bind(PersistenceManager.class).toProvider(
				PersistenceManagerProvider.class);
	}
}
