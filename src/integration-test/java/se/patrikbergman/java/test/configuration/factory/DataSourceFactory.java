package se.patrikbergman.java.test.configuration.factory;

import se.patrikbergman.java.bandapplication.externalresource.jdbc.DataSource;
import se.patrikbergman.java.test.configuration.environment.Domain;
import se.patrikbergman.java.test.configuration.environment.EnvResourceLoader;
import se.patrikbergman.java.test.configuration.environment.Environment;

import java.io.IOException;
import java.util.Properties;

public final class DataSourceFactory {

	public static DataSource createDataSource(final Environment environment, final Domain domain) throws IOException, IOException {
		final Properties properties = EnvResourceLoader.readEnvProperties(environment);
		final String domainName = domain.getName();
		return new DataSource.Builder()
				.serverDnsName(properties.getProperty(domainName + ".datasource.server.dns.name"))
				.serviceName(properties.getProperty(domainName + ".datasource.service.name"))
				.portNumber(properties.getProperty(domainName + ".datasource.port"))
				.userName(properties.getProperty(domainName + ".datasource.user"))
				.password((properties.getProperty(domainName + ".datasource.password")))
				.driverName(properties.getProperty(domainName + ".datasource.driver"))
				.build();
	}

	/*
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
	*/
}

