package se.patrikbergman.java.test.fixture.environment.utilies;

import se.patrikbergman.java.test.fixture.environment.Environment;
import se.patrikbergman.java.utility.resource.ResourceProperties;

import java.io.IOException;
import java.util.Properties;

public class EnvironmentProperties {

	private final Properties properties;

	public EnvironmentProperties(final Environment environment) throws IOException {
		final String propertiesFileName = environment.getName() + "-environment.properties";
		this.properties = new ResourceProperties(propertiesFileName).getProperties();
	}

	public Properties getProperties() {
		return properties;
	}
}