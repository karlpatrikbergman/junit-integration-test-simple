package se.patrikbergman.java.test.configuration.environment;

import se.patrikbergman.java.utility.resource.ResourceProperties;

import java.io.IOException;
import java.util.Properties;

public class EnvResourceLoader {

	public static Properties readEnvProperties(final Environment environment) throws IOException {
		final String propertiesFileName = environment.getName() + "-evironment.properties";
		return new ResourceProperties(propertiesFileName).getProperties();
	}
}