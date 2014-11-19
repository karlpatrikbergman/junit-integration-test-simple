package se.patrikbergman.java.bandapplication.ejb;

import com.google.common.base.Preconditions;
import se.patrikbergman.java.bandapplication.externalresource.jdbc.DataSource;

public class BandBean {
	private final String className = BandBean.class.getSimpleName();
	private final DataSource europeDataSource;
	private final DataSource northAmericaDataSource;
	private final DataSource asiaDataSource;

	public BandBean(final DataSource europeDataSource, final DataSource northAmericaDataSource, final DataSource asiaDataSource) {
		Preconditions.checkNotNull(europeDataSource, getInitializationError());
		Preconditions.checkNotNull(northAmericaDataSource, getInitializationError());
		Preconditions.checkNotNull(asiaDataSource, getInitializationError());

		this.europeDataSource = europeDataSource;
		this.northAmericaDataSource = northAmericaDataSource;
		this.asiaDataSource = asiaDataSource;
	}

	private String getInitializationError() {
		return String.format("Failed to initialize %s. %s cannot be null", className, DataSource.class.getSimpleName());
	}

	public void europeDataSourceDependentMethod() {
		logExecution(europeDataSource);
	}

	public void northAmericaDataSourceDependentMethod() {
		logExecution(northAmericaDataSource);
	}

	public void asiaDataSourceDependentMethod() {
		logExecution(asiaDataSource);
	}

	private void logExecution(DataSource dataSource) {
		System.out.println(String.format("- %s: Executing data source dependent command. (jdbc-url: %s)", className,
				dataSource.getConnection().getURL()));
	}
}