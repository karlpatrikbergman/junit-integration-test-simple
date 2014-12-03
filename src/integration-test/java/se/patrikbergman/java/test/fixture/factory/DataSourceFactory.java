package se.patrikbergman.java.test.fixture.factory;

import se.patrikbergman.java.bandapplication.externalresource.jdbc.DataSource;
import se.patrikbergman.java.test.fixture.domain.Domain;
import se.patrikbergman.java.test.fixture.environment.utilies.EnvironmentProperties;
import se.patrikbergman.java.test.fixture.environment.Environment;

import java.io.IOException;
import java.util.Properties;

public final class DataSourceFactory {

	public static DataSource createDataSource(final Environment environment, final Domain domain) throws IOException, IOException {
		final Properties properties = new EnvironmentProperties(environment);
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
}

