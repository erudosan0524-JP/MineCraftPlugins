package com.github.jp.erudo.ebowspleef2.runnable;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.item.Items;

public class EquipmentObserver extends BukkitRunnable {

	private Main plg;

	public EquipmentObserver(Main plg) {
		this.plg = plg;
	}

	@Override
	public void run() {
		for(Player p : plg.getServer().getOnlinePlayers()) {
			ItemStack handItem = p.getInventory().getItemInMainHand();
			if(handItem != null && handItem.getType() == Material.BOW) {
				if(handItem.hasItemMeta()) {
					if(ChatColor.stripColor(handItem.getItemMeta().getDisplayName().toString()).equals(ChatColor.stripColor(Items.bow1Name))) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,2 *20,2), true);
					} else if(ChatColor.stripColor(handItem.getItemMeta().getDisplayName().toString()).equals(ChatColor.stripColor(Items.bow2Name))) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,2 * 20,2), true);
					} else if(ChatColor.stripColor(handItem.getItemMeta().getDisplayName().toString()).equals(ChatColor.stripColor(Items.bow3Name))) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,2 * 20,2),true);
						p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,2 * 20, 2),true);
					}
				}
			}

		}

	}

}
