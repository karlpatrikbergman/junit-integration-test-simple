package se.patrikbergman.java.test.fixture.environment;

public enum Environment {
	DEV("dev"),
	TEST("test"),
	QA("qa");

	private final String name;

	Environment(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static Environment fromValue(String value) throws IllegalArgumentException{
		for(Environment env: Environment.values()) {
			if(env.getName().equals(value)) {
				return env;
			}
		}
		throw new IllegalArgumentException("Unknown value. Failed to create enum Env");
	}
}
