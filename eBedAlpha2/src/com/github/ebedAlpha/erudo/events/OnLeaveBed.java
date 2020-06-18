package com.github.ebedAlpha.erudo.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ebedAlpha.erudo.Utils;

public class OnLeaveBed implements Listener {
	public OnLeaveBed(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onLeaveBed(PlayerBedLeaveEvent e) {
		Utils.initPlayers();
	}
}
