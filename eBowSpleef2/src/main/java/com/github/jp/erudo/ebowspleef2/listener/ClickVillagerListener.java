package com.github.jp.erudo.ebowspleef2.listener;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.enums.GameState;
import com.github.jp.erudo.ebowspleef2.item.ItemManager;
import com.github.jp.erudo.ebowspleef2.item.Items;
import com.github.jp.erudo.ebowspleef2.utils.MessageManager;

public class ClickVillagerListener implements Listener {

	private Main plg;

	//名前
	private final String VillagerName = ChatColor.GREEN + "SettingVillager";

	public ClickVillagerListener(Main main) {
		this.plg = main;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onClickVillager(PlayerInteractEntityEvent e) {
		if(!(plg.getCurrentGameState() == GameState.PREPARE)){
			return;
		}


		Entity entity = e.getRightClicked();

		if (entity.getType() != EntityType.VILLAGER) {
			return;
		}

		if(entity.getCustomName() == null) {
			return;
		}

		if (!entity.getCustomName().equals(VillagerName)) {
			return;
		}

		Villager villager = (Villager) entity;

		if (villager.hasAI()) {
			return;
		}

		//ここから処理(村人を右クリックしたら)
		e.setCancelled(true);
		openGUI(e.getPlayer());
	}

	@EventHandler
	public void onClickInventory(InventoryClickEvent e) {
		if(!(plg.getCurrentGameState() == GameState.PREPARE)){
			e.setCancelled(true);
			return;
		}

		if (Objects.isNull(e.getCurrentItem()) || e.getCurrentItem().getType().equals(Material.AIR)
				|| !e.getCurrentItem().hasItemMeta()) {
			return;
		}

		//インベントリ判定
		if (Objects.isNull(e.getClickedInventory()))
			return;

		if (e.getClickedInventory().getSize() != 9)
			return;

		if(!ChatColor.stripColor(e.getClickedInventory().getTitle()).equals(ChatColor.stripColor(VillagerName))) {
			return;
		}


		Player player = (Player) e.getWhoClicked();
		ItemManager itemManager = new ItemManager();


		ItemStack bow = itemManager.makeBow(Items.bowName,1, Items.bowDesc1,Items.bowDesc2);
		ItemStack bow1 = itemManager.makeBow(Items.bow1Name, 1, Items.bow1Desc1,Items.bow1Desc2);
		ItemStack bow2 = itemManager.makeBow(Items.bow2Name, 1, Enchantment.ARROW_KNOCKBACK, 1 ,Items.bow2Desc1,Items.bow2Desc2,Items.bow2Desc3);
		ItemStack bow3 = itemManager.makeBow(Items.bow3Name, 1, Items.bow3Desc1,Items.bow3Desc2,Items.bow3Desc3);

		String judgeItem = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().toString());

		switch (e.getCurrentItem().getType()) {
		case BOW:
			//名前判定
			if (judgeItem.equals(ChatColor.stripColor(Items.bowName))) {
				//ほかの種類の弓を持っているかどうか
				if (player.getInventory().contains(Material.BOW)) {
					player.closeInventory();
					MessageManager.sendMessage(player, "弓は一つまでしか持てません。");
					return;
				}

				player.getInventory().addItem(bow);
				player.getInventory().addItem(new ItemStack(Material.ARROW, 64));

			} else if (judgeItem.equals(ChatColor.stripColor(Items.bow1Name))) {
				if (player.getInventory().contains(Material.BOW)) {
					player.closeInventory();
					MessageManager.sendMessage(player, "弓は一つまでしか持てません。");
					return;
				}

				player.getInventory().addItem(new ItemStack(Material.ARROW, 64));
				player.getInventory().addItem(bow1);

			} else if (judgeItem.equals(ChatColor.stripColor(Items.bow2Name))) {
				if (player.getInventory().contains(Material.BOW)) {
					player.closeInventory();
					MessageManager.sendMessage(player, "弓は一つまでしか持てません。");
					return;
				}

				player.getInventory().addItem(new ItemStack(Material.ARROW, 64));
				player.getInventory().addItem(bow2);

			}else if(judgeItem.equals(ChatColor.stripColor(Items.bow3Name))) {
				if(player.getInventory().contains(Material.BOW)) {
					player.closeInventory();
					MessageManager.sendMessage(player, "弓は一つまでしか持てません。");
					return;
				}

				player.getInventory().addItem(new ItemStack(Material.ARROW,64));
				player.getInventory().addItem(bow3);
			}
			e.setCancelled(true);
			break;

		default:
			break;
		}
	}

	private void openGUI(Player player) {
		ItemManager itemManager = new ItemManager();
		Inventory inv = Bukkit.createInventory(null, 9, VillagerName);

		ItemStack bow = itemManager.makeBow(Items.bowName, 1, Items.bowDesc1,Items.bowDesc2);
		ItemStack bow1 = itemManager.makeBow(Items.bow1Name, 1, Items.bow1Desc1,Items.bow1Desc2);
		ItemStack bow2 = itemManager.makeBow(Items.bow2Name, 1, Enchantment.ARROW_KNOCKBACK, 1 ,Items.bow2Desc1,Items.bow2Desc2,Items.bow2Desc3);
		ItemStack bow3 = itemManager.makeBow(Items.bow3Name, 1, Items.bow3Desc1,Items.bow3Desc2,Items.bow3Desc3);

		inv.setItem(1, bow);
		inv.setItem(3, bow1);
		inv.setItem(5, bow2);
		inv.setItem(7, bow3);

		player.openInventory(inv);
	}
}
