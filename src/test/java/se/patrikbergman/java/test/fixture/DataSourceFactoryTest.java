package se.patrikbergman.java.test.fixture;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import se.patrikbergman.java.bandapplication.externalresource.jdbc.DataSource;
import se.patrikbergman.java.test.fixture.factory.DataSourceFactory;
import se.patrikbergman.java.test.fixture.domain.Domain;
import se.patrikbergman.java.test.fixture.environment.Environment;
import se.patrikbergman.java.utility.JsonString;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class DataSourceFactoryTest {
	private final String className = DataSourceFactoryTest.class.getSimpleName();

	@Rule
	public TestName testName = new TestName();

	@Test
	public void createDevEnvironmentEuropeDataSource() throws IOException, SQLException {
		commonTest(Environment.DEV, Domain.EU);
	}

	@Test
	public void createTestEnvironmentEuropeDataService() throws IOException, SQLException {
		commonTest(Environment.TEST, Domain.EU);
	}

	@Test
	public void createQaEnvironmentEuropeDataSource() throws IOException, SQLException {
		commonTest(Environment.QA, Domain.EU);
	}

	private void commonTest(Environment environment, Domain domain) throws IOException, SQLException {
		System.out.println(String.format("%s: %s: %s()", className, environment.getName(), testName.getMethodName()));
		final DataSource dataSource = DataSourceFactory.createDataSource(environment, domain);
		assertNotNull(dataSource);
		System.out.println(new JsonString(dataSource));
	}
}
