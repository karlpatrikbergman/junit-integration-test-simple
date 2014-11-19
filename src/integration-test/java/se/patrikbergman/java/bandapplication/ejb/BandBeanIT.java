package se.patrikbergman.java.bandapplication.ejb;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import se.patrikbergman.java.bandapplication.externalresource.jdbc.DataSource;
import se.patrikbergman.java.test.fixture.domain.Domain;
import se.patrikbergman.java.test.fixture.environment.Environment;
import se.patrikbergman.java.test.fixture.environment.utilies.ResourceEnvironment;
import se.patrikbergman.java.test.fixture.rule.DataSourceConfiguration;
import se.patrikbergman.java.test.fixture.rule.annotations.DataSourceDomain;

import static org.junit.Assert.assertNotNull;


public class BandBeanIT {
	private static final String className = BandBeanIT.class.getSimpleName();
	private static final Environment environment;

	static {
		environment = new ResourceEnvironment("config.properties").getEnvironment();
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

	@Rule
	public TestName testName = new TestName();

	private static BandBean bandBean;

	@BeforeClass
	public static void setup() {
		System.out.println(String.format("%s: @BeforeClass: Instantiate %s with data sources", className,
				BandBean.class.getSimpleName()));
		bandBean = new BandBean(europeDataSource, northAmericaDataSource, asiaDataSource);
	}

	@Test
	public void executeEuropeDataSourceDependentMethod() {
		System.out.println(String.format("%s: %s()", className, testName.getMethodName()));
		assertNotNull(europeDataSource);
		bandBean.europeDataSourceDependentMethod();
	}

	@Test
	public void executeNorthAmericaDataSourceDependentMethod() {
		System.out.println(String.format("%s: %s()", className, testName.getMethodName()));
		assertNotNull(northAmericaDataSource);
		bandBean.northAmericaDataSourceDependentMethod();
	}

	@Test
	public void executeAsiaDataSourceDependentMethod() {
		System.out.println(String.format("%s: %s()", className, testName.getMethodName()));
		assertNotNull(asiaDataSource);
		bandBean.asiaDataSourceDependentMethod();
	}

	@AfterClass
	public static void tearDown() {
		System.out.println(String.format("%s: @AfterClass: Setting BandBean to null", className));
		bandBean = null;
	}
}
