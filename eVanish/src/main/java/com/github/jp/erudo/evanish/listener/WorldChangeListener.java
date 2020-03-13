package com.github.jp.erudo.evanish.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import com.github.jp.erudo.evanish.Main;

public class WorldChangeListener implements Listener {

	private Main plg;

	public WorldChangeListener(Main plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onChangeWorld(PlayerChangedWorldEvent e) {
		Player player = e.getPlayer();

		if(plg.getIsVanished().get(player)) { //true
			for(Player online : Bukkit.getServer().getOnlinePlayers()) {
				online.hidePlayer(plg, player);
			}
		}
	}
}
