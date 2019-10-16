package com.github.erudo.advancedec.listener;

import java.io.IOException;

import org.bukkit.Bukkit;
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
					Inventory inventory;
					//インベントリが存在している
					try {
						inventory = plg.getSavedInventory(player);
						player.openInventory(inventory);

					//インベントリが存在していない
					}catch(NullPointerException ex) {
						inventory = Bukkit.getServer().createInventory(null, 9 * plg.getChestRow(),"AdvancedEnderChest");
						plg.saveInventory(player, inventory);
						player.openInventory(inventory);

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}
