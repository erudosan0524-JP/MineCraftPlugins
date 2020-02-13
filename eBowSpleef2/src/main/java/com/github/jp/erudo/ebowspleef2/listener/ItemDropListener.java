package com.github.jp.erudo.ebowspleef2.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.github.jp.erudo.ebowspleef2.Main;

public class ItemDropListener implements Listener {

	public ItemDropListener(Main plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onItemdrop(PlayerDropItemEvent  e) {
		if(e.getPlayer() instanceof Player) {
			e.setCancelled(true);
		}
	}
}
