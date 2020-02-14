package com.github.jp.erudo.ebowspleef2.listener;

import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.item.Items;

import net.md_5.bungee.api.ChatColor;

public class EntityDamageListener implements Listener {

	public EntityDamageListener(Main plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent e) {
		Entity entity = e.getEntity();
		Entity damager = e.getDamager();

		if(entity.getType() == EntityType.VILLAGER) {
			if(Objects.isNull(entity.getCustomName())) {
				return;
			}

			if(ChatColor.stripColor(entity.getCustomName()).equals("SettingVillager")) {
				e.setCancelled(true);
			}
		} else if(entity.getType() == EntityType.PLAYER && damager.getType() == EntityType.PLAYER) {
			Player player = (Player) damager;
			if(player.getItemInHand() != null) {
				ItemStack item = player.getItemInHand();
				if(item.getType() == Material.BOW && item.hasItemMeta()) {
					if(ChatColor.stripColor(item.getItemMeta().getDisplayName().toString()).equals(Items.bow2Name)) {
						e.setCancelled(true);

						//ノックバック処理
						Vector vector1 = entity.getLocation().toVector();
						Vector vector2 = player.getLocation().toVector();

						Vector vector = vector1.subtract(vector2).setY(0.5);

						entity.setVelocity(vector);
					}
				}
			}
		}
	}

}
