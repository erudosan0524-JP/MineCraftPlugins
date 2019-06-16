package com.github.erudo0524.eoni2.enums;

public enum Teams {
	ONI("ONI"), PLAYER("PLAYER");

	private final String name;

	private Teams(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
