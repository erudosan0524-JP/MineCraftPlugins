package com.github.jp.erudo.ebowspleef2.runnable;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.item.ItemManager;

public class SplashPotionRunnable extends BukkitRunnable {

	private Main plg;
	private BukkitTask task;
	private int count;
	private Player throwPlayer;

	private final String potName = ChatColor.DARK_PURPLE + "魔法のポーション";
	private final String potDesc1 = ChatColor.GRAY + "相手を10秒間移動不能にする。";
	private final String potDesc2 = ChatColor.GRAY + "「ふつうの弓」と一緒に使うことができる。";
	private final String potDesc3 = ChatColor.AQUA + "使用した15秒後に自動補充される";

	public SplashPotionRunnable(Main plg, int count,Player player) {
		this.plg = plg;
		this.count = count;
	}

	@Override
	public void run() {
		if(count <= 0) {
			List<String> potionLore = new ArrayList<String>();
			potionLore.add(potDesc1);
			potionLore.add(potDesc2);
			potionLore.add(potDesc3);

			ItemManager itemManager = new ItemManager();
			ItemStack potion = itemManager.makePotion(potName, potionLore, PotionEffectType.SLOW,
					PotionEffectType.BLINDNESS, PotionEffectType.CONFUSION, 127, 127, 1, Color.BLACK);

			throwPlayer.getInventory().addItem(potion);

			plg.getServer().getScheduler().cancelTask(task.getTaskId());
		}

		count--;
	}

	public void setTask(BukkitTask task) {
		this.task = task;
	}

}
