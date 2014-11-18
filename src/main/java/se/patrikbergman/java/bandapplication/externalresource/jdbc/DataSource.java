package se.patrikbergman.java.bandapplication.externalresource.jdbc;

import com.google.common.base.Objects;

public class DataSource {
	private final String driverName;
	private final String serverDnsName;
	private final String portNumber;
	private final String serviceName;
	private final String userName;
	private final String password;
	private final Connection connection;

	public String getDriverName() {
		return driverName;
	}

	public String getServerDnsName() {
		return serverDnsName;
	}

	public String getPortNumber() {
		return portNumber;
	}

	public String getServiceName() {
		return serviceName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Connection getConnection() {
		return connection;
	}

	private DataSource(Builder builder) {
		driverName = builder.driverName;
		serverDnsName = builder.serverDnsName;
		portNumber = builder.portNumber;
		serviceName = builder.serviceName;
		userName = builder.userName;
		password = builder.password;
		connection = builder.connection;
	}

	public static final class Builder {
		private String driverName;
		private String serverDnsName;
		private String portNumber;
		private String serviceName;
		private String userName;
		private String password;
		private Connection connection;

		public Builder() {
		}

		public Builder driverName(String driverName) {
			this.driverName = driverName;
			return this;
		}

		public Builder serverDnsName(String serverDnsName) {
			this.serverDnsName = serverDnsName;
			return this;
		}

		public Builder portNumber(String portNumber) {
			this.portNumber = portNumber;
			return this;
		}

		public Builder serviceName(String serviceName) {
			this.serviceName = serviceName;
			return this;
		}

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public DataSource build() {
			this.connection = new Connection(String.format("jdbc:%s:thin:@%s:%s/%s", driverName, serverDnsName,
					portNumber, serviceName));
			return new DataSource(this);
		}
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.add("driverName", driverName)
				.add("serverDnsName", serverDnsName)
				.add("portNumber", portNumber)
				.add("serviceName", serviceName)
				.add("userName", userName)
				.add("password", password)
				.add("connection", connection)
				.toString();
	}
}
