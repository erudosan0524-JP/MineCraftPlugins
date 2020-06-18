package com.github.ebedAlpha.erudo.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ebedAlpha.erudo.Utils;

public class OnGotoBed implements Listener {

	JavaPlugin plg;

	public OnGotoBed(JavaPlugin plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onGotoBed(PlayerBedEnterEvent e) {
		Player player = e.getPlayer();
		Utils.removePlayer(player);
		if(Utils.getPlayers().size() >= Bukkit.getOnlinePlayers().size() / 2) {
			player.getWorld().setTime(18000L);
		}
	}
}
