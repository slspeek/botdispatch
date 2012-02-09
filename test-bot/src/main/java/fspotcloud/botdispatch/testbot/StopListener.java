package fspotcloud.botdispatch.testbot;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class StopListener extends Thread {

	final static private Logger log = Logger.getLogger(StopListener.class.getName());
	final int stopPort;
	private ServerSocket server;

	@Inject
	public StopListener(@Named("stop port") int port) {
		this.stopPort = port;
	}

	public void run() {
		try {
			server = new ServerSocket(stopPort);
			server.accept();
		} catch (IOException e) {
			log.warning("Could not listen on port " + stopPort);
			log.warning("Aborting on request");
			System.exit(-1);
		}
		System.exit(0);
	}

}
