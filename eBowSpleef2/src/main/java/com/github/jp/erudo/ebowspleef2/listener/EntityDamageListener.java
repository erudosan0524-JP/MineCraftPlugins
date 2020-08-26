package com.github.jp.erudo.ebowspleef2.listener;

import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.enums.GameState;
import com.github.jp.erudo.ebowspleef2.enums.Teams;
import com.github.jp.erudo.ebowspleef2.item.Items;

import net.md_5.bungee.api.ChatColor;

public class EntityDamageListener implements Listener {

	private Main plg;

	public EntityDamageListener(Main plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
		this.plg = plg;
	}

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

		} else if(entity instanceof Player && damager instanceof Player) {
			Player player = (Player) damager;
			if(player.getInventory().getItemInMainHand() != null) {
				ItemStack item = player.getInventory().getItemInMainHand();
				if(item.getType() == Material.BOW && item.hasItemMeta()) {
					if(ChatColor.stripColor(item.getItemMeta().getDisplayName().toString()).equals(ChatColor.stripColor(Items.bow2Name))) {
						e.setCancelled(true);

						//ノックバック処理
						Vector vector1 = entity.getLocation().toVector();
						Vector vector2 = player.getLocation().toVector();

						Vector vector = vector1.subtract(vector2).setY(0.5).multiply(0.6D);

						entity.setVelocity(vector);
					}
				}
			}
		//プレイヤーが弓に当たった時
		} else if (damager instanceof Arrow && entity instanceof Player ) {

			if (!plg.getCurrentGameState().equals(GameState.GAMING)) {
				return;
			}


			Arrow arrow = (Arrow) damager;

			if(!(arrow.getShooter() instanceof Player)) {
				return;
			}

			Player shooter = (Player) arrow.getShooter();
			Player hitPlayer = (Player) entity;

			if(shooter != hitPlayer) {
				if(plg.getTeam(Teams.BLUE).hasEntry(hitPlayer.getName()) && plg.getTeam(Teams.BLUE).hasEntry(shooter.getName())) {
					e.setCancelled(true);
					hitPlayer.setHealth(hitPlayer.getHealth() + 10);
					hitPlayer.getLocation().getWorld().spawnParticle(Particle.HEART, hitPlayer.getLocation(), 5, 1, 1, 1);
				}

				if(plg.getTeam(Teams.RED).hasEntry(hitPlayer.getName()) && plg.getTeam(Teams.RED).hasEntry(shooter.getName())) {
					e.setCancelled(true);
					hitPlayer.setHealth(hitPlayer.getHealth() + 10);
					hitPlayer.getLocation().getWorld().spawnParticle(Particle.HEART, hitPlayer.getLocation(), 5, 1, 1, 1);
				}

				ItemStack item = shooter.getInventory().getItemInMainHand();
				if(item != null && item.getType() == Material.BOW && item.hasItemMeta()) {
					if(ChatColor.stripColor(item.getItemMeta().getDisplayName().toString()).equals(ChatColor.stripColor(Items.bow2Name)) ) {

						Vector knockback = arrow.getVelocity().setY(0.5).multiply(1.0D);

						hitPlayer.setVelocity(knockback);

					}
				}


				hitPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 1));
				hitPlayer.getLocation().getWorld().spawnParticle(Particle.VILLAGER_HAPPY,
						hitPlayer.getLocation(), 10,1,1,1);
				hitPlayer.getLocation().getWorld().playSound(hitPlayer.getLocation(), Sound.BLOCK_ANVIL_PLACE,
						(float) 0.5, 5);

			} else {
				ItemStack bow = shooter.getInventory().getItemInMainHand();

				if(bow != null && bow.getType() == Material.BOW) {
					if(bow.hasItemMeta()) {
						//もし手に持っている弓がアイオロスだったら
						if(ChatColor.stripColor(bow.getItemMeta().getDisplayName().toString()).equals(ChatColor.stripColor(Items.bow1Name))) {

							Vector boostVec = hitPlayer.getLocation().getDirection().normalize().multiply(2.5).setY(1);

							hitPlayer.setVelocity(boostVec);
						}
					}
				}
			}


		}
	}

}
