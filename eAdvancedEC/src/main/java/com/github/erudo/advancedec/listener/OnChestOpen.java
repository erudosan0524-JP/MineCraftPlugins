package com.github.erudo.advancedec.listener;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import com.github.erudo.advancedec.Main;

public class OnChestOpen implements Listener {

	private Main plg;

	public OnChestOpen(Main main) {
		this.plg = main;
		main.getServer().getPluginManager().registerEvents(this, main);

	}

	@EventHandler(ignoreCancelled=true)
	public void onInteractChest(PlayerInteractEvent e) {

		Player player = e.getPlayer();

		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock() != null) {
				if(e.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
					e.setCancelled(true);
					Inventory inventory = null;
					try {
						inventory = plg.getSavedInventory(player);
					} catch (IOException e2) {
						e2.printStackTrace();
					}

					player.openInventory(inventory);
				}
			}
		}
	}
}
