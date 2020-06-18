package com.github.ebed.erudo.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ebed.erudo.Utils;

public class OnLeaveBed implements Listener {

	public OnLeaveBed(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onLeaveBed(PlayerBedLeaveEvent e) {
		Player player = e.getPlayer();
		Utils.addPlayer(player);
	}
}
