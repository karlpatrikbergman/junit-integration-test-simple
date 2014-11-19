package se.patrikbergman.java.test.fixture.rule;

import com.google.common.base.Preconditions;
import org.junit.rules.ExternalResource;
import se.patrikbergman.java.bandapplication.externalresource.jdbc.DataSource;
import se.patrikbergman.java.test.fixture.factory.DataSourceFactory;
import se.patrikbergman.java.test.fixture.domain.Domain;
import se.patrikbergman.java.test.fixture.environment.Environment;
import se.patrikbergman.java.test.fixture.rule.annotations.DataSourceDomain;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DataSourceConfiguration extends ExternalResource {
	private final String className = DataSourceConfiguration.class.getSimpleName();
	private final Class target;
	private final Environment environment;
	private final List<DataSource> dataSources = new ArrayList<>();

	public DataSourceConfiguration(Class target, Environment environment) {
		Preconditions.checkNotNull(target, "Test class cannot be null");
		Preconditions.checkNotNull(environment, "Execution environment cannot be null");

		System.out.println(String.format("%s: constructor()", className));
		this.target = target;
		this.environment = environment;
	}

//	@Override
//	public Statement apply(final Statement base, final Description description) {
//		System.out.println(String.format("In %s apply", className));
//		return new Statement() {
//			@Override
//			public void evaluate() throws Throwable {
//				for (Field f : target.getFields()) {
//					if (f.isAnnotationPresent(DataSourceDomain.class)) {
//						final DataSourceDomain dataSourceDomain = f.getAnnotation(DataSourceDomain.class);
//						final Domain domain = dataSourceDomain.domain();
//						final DataSource dataSource = DataSourceFactory.createDataSource(Environment.DEV, domain);
//						f.set(target, dataSource);
//						dataSources.add(dataSource);
//					}
//				}
//				base.evaluate();
//			}
//		};
//	}

	@Override
	public void before() throws IllegalAccessException, IOException {
		System.out.println(String.format("%s: before(): Set data sources according to data source field annotations in %s and test execution " +
				"environment '%s'", className, target.getSimpleName(), environment));

		for (Field f : target.getFields()) {
			if (f.isAnnotationPresent(DataSourceDomain.class)) {
				final DataSourceDomain dataSourceDomain = f.getAnnotation(DataSourceDomain.class);
				final Domain domain = dataSourceDomain.domain();
				final DataSource dataSource = DataSourceFactory.createDataSource(environment, domain);
				f.set(target, dataSource);
				dataSources.add(dataSource);
			}
		}
	}

	@Override
	public void after() {
		System.out.println(String.format("%s: after(): Close data source connections", className));

		closeConnections();
		setDataSourceFieldsToNull();
	}

	private void closeConnections() {
		for(DataSource dataSource : dataSources) {
			if(dataSource != null && dataSource.getConnection() != null) {
				dataSource.getConnection().close();
				System.out.println(String.format("- %s: Closed data source with url %s", className, dataSource.getConnection().getURL()));
			}
		}
	}

	private void setDataSourceFieldsToNull() {
		System.out.println(String.format("%s: after(): Setting data source fields to null", className));
		for (Field f : target.getFields()) {
			if (f.isAnnotationPresent(DataSourceDomain.class)) {
				f.setAccessible(true);
				try {
					f.set(target, null);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
