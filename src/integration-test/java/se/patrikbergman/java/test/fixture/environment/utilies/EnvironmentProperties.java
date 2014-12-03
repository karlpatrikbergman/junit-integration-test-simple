package se.patrikbergman.java.test.fixture.environment.utilies;

import se.patrikbergman.java.test.fixture.environment.Environment;
import se.patrikbergman.java.utility.resource.ResourceInputStream;

import java.io.IOException;
import java.util.Properties;

public class EnvironmentProperties extends Properties {

	public EnvironmentProperties(final Environment environment) throws IOException {
		final String propertiesFileName = environment.getName() + "-environment.properties";
		super.load(new ResourceInputStream(propertiesFileName));
	}
}