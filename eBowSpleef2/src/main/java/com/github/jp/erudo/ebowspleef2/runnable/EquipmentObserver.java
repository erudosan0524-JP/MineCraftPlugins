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
			//手に持ってるアイテムの検知
			if(handItem != null && handItem.getType() == Material.BOW) {
				if(handItem.hasItemMeta()) {
					if(ChatColor.stripColor(handItem.getItemMeta().getDisplayName().toString()).equals(ChatColor.stripColor(Items.bow1Name))) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,2 *20,2), true);
					} else if(ChatColor.stripColor(handItem.getItemMeta().getDisplayName().toString()).equals(ChatColor.stripColor(Items.bow2Name))) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,2 * 20,127), true);
					} else if(ChatColor.stripColor(handItem.getItemMeta().getDisplayName().toString()).equals(ChatColor.stripColor(Items.bow3Name))) {
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,2 * 20,127),true);
						p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,2 * 20, 20),true);
					}
				}
			//何も持ってない時 || 弓以外のアイテムを持っている時
			} else {
				if(p.getInventory().contains(Material.BOW)) {
					ItemStack bow = p.getInventory().getItem(p.getInventory().first(Material.BOW));

					if(bow.hasItemMeta()) {
						if(ChatColor.stripColor(bow.getItemMeta().getDisplayName().toString()).equals(ChatColor.stripColor(Items.bow2Name))) {
							p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,2 * 20,0), true);
							p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,2 * 20,0), true);
						}
					}

				}

			}
		}

	}

}
