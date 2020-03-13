package com.github.jp.erudo.evanish.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.jp.erudo.evanish.Main;

public class PlayerJoinListener implements Listener {

	private Main plg;

	public PlayerJoinListener(Main plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();

		plg.getIsVanished().put(player, false);
	}
}
