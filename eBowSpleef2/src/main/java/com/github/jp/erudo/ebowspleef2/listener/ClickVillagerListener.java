package com.github.jp.erudo.ebowspleef2.listener;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
import com.github.jp.erudo.ebowspleef2.utils.ItemManager;

import net.minecraft.server.v1_12_R1.Material;

public class ClickVillagerListener implements Listener {

	private Main plg;

	//名前とか
	private final String VillagerName = ChatColor.GREEN + "SettingVillager";
	private final String bow1Name = ChatColor.LIGHT_PURPLE + "アイオロス"; //ギリシャ神話に登場する風神アイオロスから
	private final String bow1Lore = ChatColor.GRAY + "風の神の加護を受けている。持つと移動速度が上昇する弓。";
	private final String bow2Name = ChatColor.GREEN + "ウラノス"; //ギリシャ神話。天空神ウラノス(ウラーノス）から
	private final String bow2Lore = ChatColor.GRAY + "空の神の加護を受けている。持つと跳躍力が上昇する弓。";

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

	@SuppressWarnings("unlikely-arg-type")
	@EventHandler
	public void onClickInventory(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		ItemManager itemManager = new ItemManager();

		ItemStack bow1 = itemManager.makeBow(bow1Name, bow1Lore, 1);
		ItemStack bow2 = itemManager.makeBow(bow2Name, bow2Lore, 1);

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
			System.out.println(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().toString()));
			if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().toString()).equals("アイオロス")) {
				player.getInventory().addItem(bow1);
				System.out.println("Bow1 given");
			} else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName().toString()).equals("ウラノス")) {
				player.getInventory().addItem(bow2);
				System.out.println("Bow2 given");
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

		ItemStack bow1 = itemManager.makeBow(bow1Name, bow1Lore, 1);
		ItemStack bow2 = itemManager.makeBow(bow2Name, bow2Lore, 1);

		inv.setItem(1, bow1);
		inv.setItem(3, bow2);

		player.openInventory(inv);
	}
}
