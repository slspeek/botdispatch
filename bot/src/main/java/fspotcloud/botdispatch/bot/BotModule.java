package fspotcloud.botdispatch.bot;

import java.net.URL;

import javax.inject.Singleton;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class BotModule extends AbstractModule {

	protected void configure() {
		bind(BotDispatchServer.class).to(BotDispatchServerImpl.class);
		bind(Pauser.class).to(PauserImpl.class);
		bind(RemoteExecutor.class).to(RemoteExecutorImpl.class).in(Singleton.class);
		bind(String.class).annotatedWith(Names.named("endpoint")).toInstance(
				"http://" + System.getProperty("endpoint", "localhost:8080") + "/xmlrpc/"
						+ System.getProperty("bot.secret"));
		bind(Integer.class).annotatedWith(Names.named("pause")).toInstance(
				Integer.valueOf(System.getProperty("pause", "10")));
		install(new FactoryModuleBuilder().implement(CommandWorker.class,
				CommandWorkerImpl.class).build(CommandWorkerFactory.class));
	}

	@Provides
	XmlRpcClient provideXmlRpcClient(@Named("endpoint") String endpoint)
			throws Exception {
		// create configuration
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(endpoint));
		config.setEnabledForExtensions(true);
		config.setConnectionTimeout(60 * 1000);
		config.setReplyTimeout(60 * 1000);
		XmlRpcClient controller = new XmlRpcClient();
		// use Commons HttpClient as transport
		controller.setTransportFactory(new XmlRpcCommonsTransportFactory(
				controller));
		// set configuration
		controller.setConfig(config);
		return controller;
	}
}
