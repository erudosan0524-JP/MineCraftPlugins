package com.github.jp.erudo.eanticheat.checks;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.jp.erudo.eanticheat.utils.MessageManager;

public class Speed implements HackChecker{
	/*
	 * Speedハック検知
	 * Speed speed = new Speed(Location from, Location to);
	 * speed.check();
	 * で実行。
	 */

	Player player;
	Location from, to;

	public Speed(Player p) {
		this.player = p;
	}

	public void check() {
		if(player.isFlying()) return;
		if(player.getVehicle() != null) return;
		player.sendMessage(MessageManager.prefix + "text");
	}

}
