package com.github.jp.erudo.ebowspleef2.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.utils.ItemManager;

public class ClickEntityListener implements Listener {

	private Main plg;

	//名前とか
	private final String VillagerName = "SettingVillager";
	private final String bow1Name = "速疾弓"; //速疾弓(ソクシツキュウ) 速疾鬼（羅刹天 人を惑わす悪魔）からもじったもの。
	private final String bow1Lore = "持つと移動速度が上昇する弓。その速さで相手を惑わして隙をついて攻撃をする。";


	public ClickEntityListener(Main main) {
		this.plg = main;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onClickVillager(PlayerInteractEntityEvent e) {
		Entity entity = e.getRightClicked();

		if(entity.getType() != EntityType.VILLAGER) {
			return;
		}

		if(!entity.getCustomName().equals(ChatColor.GREEN + VillagerName)) {
			return;
		}

		Villager villager = (Villager) entity;

		if(villager.hasAI()) {
			return;
		}

		//ここから処理(村人を右クリックしたら)

	}


	private void openGUI(Player player) {
		ItemManager itemManager = new ItemManager();
		Inventory inv = Bukkit.createInventory(null, 9 * 6, ChatColor.GREEN + VillagerName);

		ItemStack bow1 = itemManager.makeBow(ChatColor.DARK_PURPLE + bow1Name, bow1Lore, 1);
	}
}
