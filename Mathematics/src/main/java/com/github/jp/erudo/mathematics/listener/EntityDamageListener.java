package com.github.jp.erudo.mathematics.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import com.github.jp.erudo.mathematics.Main;

public class EntityDamageListener implements Listener {

	private Main plg;

	public EntityDamageListener(Main plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			return;
		}

		Player damager = (Player) e.getDamager();
		Entity entity = e.getEntity();


		entity.setVelocity(new Vector(entity.getLocation().getX() - damager.getLocation().getX(),
										entity.getLocation().getY() - damager.getLocation().getY(),
										entity.getLocation().getZ() - damager.getLocation().getZ()).normalize().multiply(-1.5D));
	}
}
