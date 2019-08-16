package com.github.erudo.ebowspleef.listener;

import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.erudo.ebowspleef.Main;


public class DamagePlayerListener implements Listener {

	Main plg;

	public DamagePlayerListener(Main main) {
		this.plg = main;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onDamagePlayer(EntityDamageByEntityEvent e) {
		//あたったのがプレイヤーでなければreturn
		if (!(e.getEntity() instanceof Player)) {
			return;
		}

		Entity entity = e.getEntity();
		Entity damager = e.getEntity();

		Player player = (Player) entity;

		if(damager instanceof Arrow) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 1));
			player.getLocation().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, player.getLocation(), 50, 5, 3, 5);
			e.setCancelled(true);
		}
	}
}
