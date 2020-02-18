package com.github.jp.erudo.mathematics.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import com.github.jp.erudo.mathematics.Main;

public class EntityDamageListener implements Listener {

	public EntityDamageListener(Main plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent e) {
		if(!(e.getDamager() instanceof Player)) {
			return;
		}

		System.out.println("event fire");
		e.setCancelled(true);

		Entity damager =  (Player) e.getDamager();
		Entity entity =  e.getEntity();

		Vector vector1 = entity.getLocation().toVector();
		Vector vector2 = damager.getLocation().toVector();

		Vector vector = vector1.subtract(vector2).setY(0.5).multiply(0.6D);

		entity.setVelocity(vector);
	}
}
