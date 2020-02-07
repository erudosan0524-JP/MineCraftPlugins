package com.github.jp.erudo.ebowspleef2.enums;

public enum Teams {
	RED("RED"),
	BLUE("BLUE"),
	SPECTATOR("SPECTATOR");

	private final String name;

	private Teams(final String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
