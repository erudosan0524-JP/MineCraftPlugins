package com.github.erudo.ebowspleef.enums;

public enum Teams {
	RED("Red"),
	BLUE("Blue"),
	SPECTATOR("Spectator");

	private final String name;

	private Teams(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
