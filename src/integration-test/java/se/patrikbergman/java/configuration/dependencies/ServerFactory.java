package se.patrikbergman.java.configuration.dependencies;

import se.patrikbergman.java.bandapplication.externalresource.server.Server;
import se.patrikbergman.java.configuration.environment.*;

public final class ServerFactory {

	public static final Server createServer(final Environment env  ) {
		switch (env) {
			case DEV:
				return new Server("dev.atg.se", "6001", "dev-user", "hokuspokus");
			case TEST:
				return new Server("test.atg.se", "7001", "test-user", "abrakadabra");
			case QA:
				return new Server("qa.atg.se", "8001", "qa-user", "simsalabim");
			default:
				throw new IllegalArgumentException("Failed to create test server for environment " + env.getName());
		}
	}
}
