package com.github.jp.erudo.ebowspleef2.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
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
import org.bukkit.potion.PotionEffectType;

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


		Player player = (Player) e.getWhoClicked();
		ItemManager itemManager = new ItemManager();

		List<String> potionLore = new ArrayList<String>();
		potionLore.add(Items.potDesc1);
		potionLore.add(Items.potDesc2);

		ItemStack bow = itemManager.makeBow(Items.bowName,1, Items.bowDesc1,Items.bowDesc2, Items.bowDesc3);
		ItemStack bow1 = itemManager.makeBow(Items.bow1Name, 1, Items.bow1Desc1,Items.bow1Desc2);
		ItemStack bow2 = itemManager.makeBow(Items.bow2Name, 1, Items.bow2Desc1,Items.bow2Desc2);
		bow2.addEnchantment(Enchantment.KNOCKBACK, 1);
		bow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		ItemStack potion = itemManager.makePotion(Items.potName, potionLore, PotionEffectType.SLOW,
				PotionEffectType.BLINDNESS, PotionEffectType.CONFUSION, 127, 127, 1, Color.BLACK);

		if (Objects.isNull(e.getCurrentItem()) || e.getCurrentItem().getType().equals(Material.AIR)
				|| !e.getCurrentItem().hasItemMeta()) {
			return;
		}

		//インベントリ判定
		if (Objects.isNull(e.getClickedInventory()))
			return;

		if (e.getClickedInventory().getSize() != 9)
			return;

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

				if (player.getInventory().contains(Material.SPLASH_POTION)) {
					player.closeInventory();
					MessageManager.sendMessage(player, "この弓はポーションといっしょに使えません。");
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

				if (player.getInventory().contains(Material.SPLASH_POTION)) {
					player.closeInventory();
					MessageManager.sendMessage(player, "この弓はポーションといっしょに使えません。");
					return;
				}
				player.getInventory().addItem(new ItemStack(Material.ARROW, 64));
				player.getInventory().addItem(bow2);

			}
			e.setCancelled(true);
			break;
		case SPLASH_POTION:
			if (judgeItem.equals(Items.potName)) {
				//弓を持っているか
				if (player.getInventory().contains(Material.BOW)) {
					//弓を持っていてかつポーションを持っている状態
					if (player.getInventory().contains(Material.SPLASH_POTION)) {
						player.closeInventory();
						MessageManager.sendMessage(player, "ポーションは一つまでしか持てません。");
						return;
					}

					ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.BOW));

					if(item.getItemMeta().getDisplayName().toString().equals(Items.bowName)) {
						player.getInventory().addItem(potion);

					} else {
						player.closeInventory();
						MessageManager.sendMessage(player, "このポーションは「ふつうの弓」と一緒にしか使えません。");
						return;
					}
				} else {
					if (player.getInventory().contains(Material.SPLASH_POTION)) {
						player.closeInventory();
						MessageManager.sendMessage(player, "ポーションは一つまでしか持てません。");
						return;
					}

					player.getInventory().addItem(potion);
				}

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

		List<String> potionLore = new ArrayList<String>();
		potionLore.add(Items.potDesc1);
		potionLore.add(Items.potDesc2);

		ItemStack bow = itemManager.makeBow(Items.bowName, 1, Items.bowDesc1,Items.bowDesc2,Items.bowDesc3);
		ItemStack bow1 = itemManager.makeBow(Items.bow1Name, 1, Items.bow1Desc1,Items.bow1Desc2);
		ItemStack bow2 = itemManager.makeBow(Items.bow2Name, 1, Items.bow2Desc1,Items.bow2Desc2);
		bow2.addEnchantment(Enchantment.KNOCKBACK, 1);
		bow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		ItemStack potion = itemManager.makePotion(Items.potName, potionLore, PotionEffectType.SLOW,
				PotionEffectType.BLINDNESS, PotionEffectType.CONFUSION, 127, 127, 1, Color.BLACK);

		inv.setItem(1, bow);
		inv.setItem(3, bow1);
		inv.setItem(5, bow2);
		inv.setItem(7, potion);

		player.openInventory(inv);
	}
}
