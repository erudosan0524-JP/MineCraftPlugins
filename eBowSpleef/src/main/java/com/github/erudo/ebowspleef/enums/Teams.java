package com.github.erudo.ebowspleef.enums;

public enum Teams {
	PLAYER("Player"),
	SPECTATOR("Spectator");

	private final String name;

	private Teams(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
