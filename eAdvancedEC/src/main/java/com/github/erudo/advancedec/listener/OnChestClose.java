package com.github.erudo.advancedec.listener;

import java.io.IOException;
import java.util.Objects;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import com.github.erudo.advancedec.Main;

public class OnChestClose implements Listener {

	private Main plg;

	public OnChestClose(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
		this.plg = main;
	}

	@EventHandler(priority = EventPriority.MONITOR,ignoreCancelled = true)
	public void onChestClose(InventoryCloseEvent e) throws IOException {
		Player player = (Player) e.getPlayer();

		if(e.getInventory().getType() != InventoryType.CHEST) return;

		if(Objects.isNull(plg.getSavedInventory(player))) return;

		Inventory inventory = e.getInventory();

		if(e.getView().getTitle() == "Chest" && e.getView().getTitle() == "AdvancedEnderChest") {
			plg.saveInventory(player, inventory);
		}

	}
}
