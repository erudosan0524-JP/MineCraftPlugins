package com.github.jp.erudo.edisablespawnegg;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventListener implements Listener {

	public EventListener(Main plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Items items = new Items();
		Player player = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(items.getSpawnEggs().contains(player.getInventory().getItemInMainHand().getType())
					|| items.getSpawnEggs().contains(player.getInventory().getItemInOffHand().getType())) {
				e.setCancelled(true);
			}
		}
	}
}
