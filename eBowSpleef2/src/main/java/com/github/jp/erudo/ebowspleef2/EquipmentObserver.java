package com.github.jp.erudo.ebowspleef2;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EquipmentObserver extends BukkitRunnable {

	private Main plg;

	public EquipmentObserver(Main plg) {
		this.plg = plg;
	}

	@Override
	public void run() {
		for(Player p : plg.getServer().getOnlinePlayers()) {

		}

	}

}
