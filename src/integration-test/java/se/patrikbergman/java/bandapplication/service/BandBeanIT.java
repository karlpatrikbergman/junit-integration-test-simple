package se.patrikbergman.java.bandapplication.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import se.patrikbergman.java.bandapplication.externalresource.jdbc.DataSource;
import se.patrikbergman.java.configuration.environment.Domain;
import se.patrikbergman.java.configuration.environment.Environment;
import se.patrikbergman.java.configuration.environment.TestExecutionEnvironment;
import se.patrikbergman.java.configuration.rule.DataSourceConfiguration;
import se.patrikbergman.java.configuration.rule.annotations.DataSourceDomain;

import static org.junit.Assert.assertNotNull;


public class BandBeanIT {
	private static final String className = BandBeanIT.class.getSimpleName();
	private static final Environment environment;

	static {
		environment = TestExecutionEnvironment.getEnvironment();
	}

	@DataSourceDomain(domain = Domain.EU)
	public static DataSource europeDataSource;

	@DataSourceDomain(domain = Domain.NA)
	public static DataSource northAmericaDataSource;

	@DataSourceDomain(domain = Domain.AS)
	public static DataSource asiaDataSource;

	@ClassRule
	public static final DataSourceConfiguration dataSourceConfiguration = new DataSourceConfiguration(BandBeanIT.class,
			BandBeanIT.environment);

	private static BandBean bandBean;

	@BeforeClass
	public static void setup() {
		System.out.println(String.format("%s: @BeforeClass: Instantiate BandBean with data sources", className));
		bandBean = new BandBean(europeDataSource, northAmericaDataSource, asiaDataSource);
	}

	@Test
	public void executeEuropeDataSourceDependentMethod() {
		System.out.println(String.format("%s: executeEuropeDataSourceDependentCommand()", className));
		assertNotNull(europeDataSource);
		bandBean.europeDataSourceDependentMethod();
	}

	@Test
	public void executeNorthAmericaDataSourceDependentMethod() {
		System.out.println(String.format("%s: executeNorthAmericaDataSourceDependentCommand()", className));
		assertNotNull(northAmericaDataSource);
		bandBean.northAmericaDataSourceDependentMethod();
	}

	@Test
	public void executeAsiaDataSourceDependentMethod() {
		System.out.println(String.format("%s: executeAsiaDataSourceDependentCommand()", className));
		assertNotNull(asiaDataSource);
		bandBean.asiaDataSourceDependentMethod();
	}

	@AfterClass
	public static void tearDown() {
		System.out.println(String.format("%s: @AfterClass: Setting BandBean to null", className));
		bandBean = null;
	}
}
