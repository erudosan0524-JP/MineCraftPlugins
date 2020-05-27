package com.github.jp.erudo.eanticheat.checks;

import org.bukkit.permissions.Permission;

public enum CheckType {

	SPEED("Speed", new Permission("eac.bypass.speed")),
	NOSLOW("NoSlowDown", new Permission("eac.bypass.noslowdown"));

	private String name;
	private Permission perm;

	private CheckType(String name, Permission perm) {
		this.name = name;
		this.perm = perm;
	}

	public String getName() {
		return name;
	}

	public Permission getPerm() {
		return perm;
	}
}
