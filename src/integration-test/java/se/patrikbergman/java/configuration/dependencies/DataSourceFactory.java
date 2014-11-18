package se.patrikbergman.java.configuration.dependencies;

import se.patrikbergman.java.bandapplication.externalresource.jdbc.DataSource;
import se.patrikbergman.java.configuration.environment.*;

public final class DataSourceFactory {

	public static final DataSource createDataSource(final Environment environment, final Domain domain) {
		final String serviceName = getServiceName(domain);
		switch (environment) {
			case DEV:
				return new DataSource.Builder()
					.driverName("oracle.jdbc.OracleDriver")
					.serverDnsName("jdbc.dev.patrikbergman.se")
					.portNumber("1521")
					.serviceName(serviceName)
					.userName("dev-user")
					.password("dev-password")
					.build();
			case TEST:
				return new DataSource.Builder()
					.driverName("oracle.jdbc.OracleDriver")
					.serverDnsName("jdbc.test.patrikbergman.se")
					.portNumber("1521")
					.serviceName(serviceName)
					.userName("test-user")
					.password("test-password")
					.build();
			case QA:
				return new DataSource.Builder()
					.driverName("oracle.jdbc.OracleDriver")
					.serverDnsName("jdbc.qa.patrikbergman.se")
					.portNumber("1521")
					.serviceName(serviceName)
					.userName("qa-user")
					.password("qa-password")
					.build();
			default:
				throw new IllegalArgumentException("Failed to create data source. Uknown environment");
		}
	}

	private static final String getServiceName(final Domain domain) {
		switch (domain) {
			case EU:
				return "europeMusicService";
			case NA:
				return "northAmericaMusicService";
			case AS:
				return "asiaMusicService";
			default:
				throw new IllegalArgumentException("Failed to get data service name. Unknown domain");
		}
	}
}

