package com.github.jp.erudo.eanticheat.utils;

import org.bukkit.entity.Player;

public class User {

	private Player player;

	public User(Player p) {
		this.player = p;
	}

	public Player getPlayer() {
		return this.player;
	}
}
