package com.github.efly.erudo;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EventListener implements Listener {
	Player player;

	public EventListener(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onPreJoin(AsyncPlayerPreLoginEvent e) {
		if(Main.isFlying.containsKey(e.getUniqueId())) {
			Main.isFlying.put(e.getUniqueId(), true);
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(Main.isFlying.containsKey(e.getPlayer().getUniqueId())) {
			if(Main.isFlying.get(e.getPlayer().getUniqueId())) {
				e.getPlayer().setAllowFlight(true);
				e.getPlayer().setFlying(true);
				e.getPlayer().setFlySpeed(0.1F);
			}
		}


	}

}
