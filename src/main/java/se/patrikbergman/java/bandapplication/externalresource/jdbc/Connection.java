package se.patrikbergman.java.bandapplication.externalresource.jdbc;

public class Connection {

	private boolean isClosed = true;
	private final String URL;

	public Connection(final String url) {
		this.URL = url;
	}

	public String getURL() {
		return URL;
	}

	public boolean isValid(final int timeOut) {
		return isClosed;
	}

	public boolean isClosed() {
		return isClosed;
	}

	public void close() {
		this.isClosed = true;
	}
}
