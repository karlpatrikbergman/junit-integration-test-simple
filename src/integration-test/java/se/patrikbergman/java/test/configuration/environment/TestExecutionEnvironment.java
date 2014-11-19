package se.patrikbergman.java.test.configuration.environment;

import se.patrikbergman.java.utility.resource.ResourceProperties;

import java.io.IOException;
import java.util.Properties;

public class TestExecutionEnvironment {

	private static final String ENVIRONMENT_KEY = "test.environment";
	private static final String ENVIRONMENT_DEFAULT_VALUE = "test";
	private static final String PROPERTIES_FILE_NAME = "config.properties";

	public static Environment getEnvironment() {
		try {
			final Properties prop = new ResourceProperties(PROPERTIES_FILE_NAME).getProperties();
			final String envName = getValue(prop, ENVIRONMENT_KEY);
			return Environment.fromValue(envName);
		}catch (IOException exception) {
			throw new RuntimeException(String.format("Failed to find resource %s. %s", PROPERTIES_FILE_NAME, exception));
		}
	}

	protected static String getValue(final Properties properties, final String key) {
		final String value = properties.getProperty(key);
		return (value.contains("$")) ? ENVIRONMENT_DEFAULT_VALUE : value;
	}
}
