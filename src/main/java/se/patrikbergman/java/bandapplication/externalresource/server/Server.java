package se.patrikbergman.java.bandapplication.externalresource.server;

public class Server {
	private final String host;
	private final String port;
	private final String user;
	private final String password;

	public Server(String host, String port, String user, String password) {
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
	}

	public void connect() {
		System.out.println(String.format("%s connecting to http://%s:%s as user %s/%s",
				Server.class.getSimpleName(), host, port, user, password));
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "ServerConfig{" +
				"host='" + host + '\'' +
				", port='" + port + '\'' +
				", user='" + user + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
