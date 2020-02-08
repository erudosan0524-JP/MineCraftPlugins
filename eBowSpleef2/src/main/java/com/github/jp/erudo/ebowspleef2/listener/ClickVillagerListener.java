package com.github.jp.erudo.ebowspleef2.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
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
import com.github.jp.erudo.ebowspleef2.utils.ItemManager;
import com.github.jp.erudo.ebowspleef2.utils.MessageManager;

public class ClickVillagerListener implements Listener {

	private Main plg;

	//名前とか
	private final String VillagerName = ChatColor.GREEN + "SettingVillager";
	private final String bowName = ChatColor.WHITE + "ふつうの弓";
	private final String bowDesc = ChatColor.GRAY + "シンプル・イズ・ザ・ベスト！ 効果が何もついてない弓。";
	private final String bow1Name = ChatColor.GOLD + "アイオロス"; //ギリシャ神話に登場する風神アイオロスから
	private final String bow1Lore = ChatColor.GRAY + "風の神の加護を受けている。持つと移動速度が上昇する弓。";
	private final String bow2Name = ChatColor.GREEN + "ウラノス"; //ギリシャ神話。天空神ウラノス(ウラーノス）から
	private final String bow2Lore = ChatColor.GRAY + "空の神の加護を受けている。持つと跳躍力が上昇する弓。";
	private final String potName = ChatColor.DARK_PURPLE + "魔法のポーション";
	private final String potDesc = ChatColor.GRAY + "相手を10秒間移動不能にする。";

	public ClickVillagerListener(Main main) {
		this.plg = main;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onClickVillager(PlayerInteractEntityEvent e) {
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
		Player player = (Player) e.getWhoClicked();
		ItemManager itemManager = new ItemManager();

		List<String> normalBowLore = new ArrayList<String>();
		normalBowLore.add(bowDesc);
		normalBowLore.add(ChatColor.GRAY + "しかし、特殊なポーションを追加で持つことができる。");

		List<String> potionLore = new ArrayList<String>();
		potionLore.add(potDesc);
		potionLore.add(ChatColor.GRAY + "「ふつうの弓」と一緒に使うことができる。");

		ItemStack bow = itemManager.makeBow(bowName, normalBowLore, 1);
		ItemStack bow1 = itemManager.makeBow(bow1Name, bow1Lore, 1);
		ItemStack bow2 = itemManager.makeBow(bow2Name, bow2Lore, 1);
		ItemStack potion = itemManager.makePotion(potName, potionLore, PotionEffectType.SLOW,
				PotionEffectType.BLINDNESS, PotionEffectType.JUMP, 127, 127, -10000, Color.BLACK);

		if (Objects.isNull(e.getCurrentItem()) || e.getCurrentItem().getType().equals(Material.AIR)
				|| !e.getCurrentItem().hasItemMeta()) {
			return;
		}

		//インベントリ判定
		if (Objects.isNull(e.getClickedInventory()))
			return;

		if (e.getClickedInventory().getSize() != 9)
			return;

		switch (e.getCurrentItem().getType()) {
		case BOW:
			//名前判定
			if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().toString()).equals("ふつうの弓")) {
				//ほかの種類の弓を持っているかどうか
				if (player.getInventory().contains(Material.BOW)) {
					player.closeInventory();
					MessageManager.sendMessage(player, "弓は一つまでしか持てません。");
					return;
				}

				player.getInventory().addItem(bow);
				player.getInventory().addItem(new ItemStack(Material.ARROW, 64));

			} else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().toString())
					.equals("アイオロス")) {
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

			} else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().toString())
					.equals("ウラノス")) {
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
			if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().toString()).equals("魔法のポーション")) {
				//弓を持っているか
				if (player.getInventory().contains(Material.BOW)) {
					//弓を持っていてかつポーションを持っている状態
					if (player.getInventory().contains(Material.SPLASH_POTION)) {
						player.closeInventory();
						MessageManager.sendMessage(player, "ポーションは一つまでしか持てません。");
						return;
					}

					ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.BOW));
					System.out.println(player.getInventory().first(Material.BOW));

					if(ChatColor.stripColor(item.getItemMeta().getDisplayName().toString()).equals("ふつうの弓")) {
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

		List<String> normalBowLore = new ArrayList<String>();
		normalBowLore.add(bowDesc);
		normalBowLore.add(ChatColor.GRAY + "しかし、特殊なポーションを追加で持つことができる。");

		List<String> potionLore = new ArrayList<String>();
		potionLore.add(potDesc);
		potionLore.add(ChatColor.GRAY + "「ふつうの弓」と一緒に使うことができる。");

		ItemStack bow = itemManager.makeBow(bowName, normalBowLore, 1);
		ItemStack bow1 = itemManager.makeBow(bow1Name, bow1Lore, 1);
		ItemStack bow2 = itemManager.makeBow(bow2Name, bow2Lore, 1);
		ItemStack potion = itemManager.makePotion(potName, potionLore, PotionEffectType.SLOW,
				PotionEffectType.BLINDNESS, PotionEffectType.JUMP, 127, 127, -10000, Color.BLACK);

		inv.setItem(1, bow);
		inv.setItem(3, bow1);
		inv.setItem(5, bow2);
		inv.setItem(7, potion);

		player.openInventory(inv);
	}
}
