package se.patrikbergman.java.test.fixture.environment.utilies;

import se.patrikbergman.java.test.fixture.environment.Environment;
import se.patrikbergman.java.utility.resource.ResourceProperties;

import java.io.IOException;
import java.util.Properties;

public class ResourceEnvironment {

	private static final String ENVIRONMENT_KEY = "test.environment";
	private static final String ENVIRONMENT_DEFAULT_VALUE = "dev";
	private static final String DEFAULT_PROPERTIES_FILE_NAME = "config.properties";
	private final Environment environment;


	public ResourceEnvironment() {
		this(DEFAULT_PROPERTIES_FILE_NAME);
	}

	public ResourceEnvironment(final String resourceOnClassPath) {
		try {
			final Properties prop = new ResourceProperties(resourceOnClassPath).getProperties();
			final String environmentValue = getValue(prop, ENVIRONMENT_KEY);
			this.environment = Environment.fromValue(environmentValue);
		}catch (IOException exception) {
			throw new RuntimeException(String.format("Failed to find resource %s. %s", resourceOnClassPath, exception));
		}
	}

	public Environment getEnvironment() {
		return this.environment;
	}

	protected static String getValue(final Properties properties, final String key) {
		final String value = properties.getProperty(key);
		return (value.contains("$")) ? ENVIRONMENT_DEFAULT_VALUE : value;
	}
}
