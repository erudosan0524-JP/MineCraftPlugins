package com.github.jp.erudo.ebowspleef2.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.github.jp.erudo.ebowspleef2.Main;

import net.md_5.bungee.api.ChatColor;

public class EntityDamageListener implements Listener {

	public EntityDamageListener(Main plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onDamageEntity(EntityDamageEvent e) {
		Entity entity = e.getEntity();

		if(entity.getType() == EntityType.VILLAGER) {
			if(ChatColor.stripColor(entity.getCustomName()).equals("SettingVillager")) {
				e.setCancelled(true);
			}

		}
	}

}
