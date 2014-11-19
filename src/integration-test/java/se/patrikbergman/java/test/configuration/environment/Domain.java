package se.patrikbergman.java.test.configuration.environment;

public enum Domain {
	EU("europe"),
	NA("north-america"),
	AS("asia");

	private final String name;

	Domain(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static Domain fromValue(String value) throws IllegalArgumentException{
		for(Domain domain: Domain.values()) {
			if(domain.getName().equals(value)) {
				return domain;
			}
		}
		throw new IllegalArgumentException("Unknown value. Failed to create enum Domain");
	}
}
