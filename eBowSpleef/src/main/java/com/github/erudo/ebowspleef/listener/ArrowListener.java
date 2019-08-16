package com.github.erudo.ebowspleef.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.erudo.ebowspleef.Main;
import com.github.erudo.ebowspleef.enums.GameState;

import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;

public class ArrowListener implements Listener {

	public List<Projectile> arrows = new ArrayList<Projectile>();

	private Main plg;

	public ArrowListener(Main main) {
		this.plg = main;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onShoot(EntityShootBowEvent e) {
		if (!plg.getGameState().equals(GameState.GAMING)) {
			return;
		}

		//矢の軌道
		arrows.add((Projectile) e.getProjectile());
		addParticleEffect((Projectile) e.getProjectile());
	}

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if (!plg.getGameState().equals(GameState.GAMING)) {
			return;
		}

		if (e.getEntity() instanceof Arrow) {
			arrows.remove(e.getEntity());

			//プレイヤーに当たったら
			if (e.getHitEntity() != null) {
				if (e.getHitEntity() instanceof Player) {
					Player player = (Player) e.getHitEntity();
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 1));
					player.getLocation().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, player.getLocation(), 50, 5,
							3, 5);
					player.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE, (float) 0.5, 5);
				}

			}

			//あたったブロックが羊毛だったら消去
			Block block = e.getHitBlock();
			Location blockLoc1 = new Location(block.getWorld(), block.getLocation().getX() + 1, block.getLocation().getY(),
					block.getLocation().getZ());
			Location blockLoc2 = new Location(block.getWorld(), block.getLocation().getX(), block.getLocation().getY(),
					block.getLocation().getZ() + 1);
			Location blockLoc3 = new Location(block.getWorld(), block.getLocation().getX() + 1, block.getLocation().getY(),
					block.getLocation().getZ() + 1);

			if (plg.getArrowRange() <= 1) {
				if (!block.getType().equals(Material.WOOL)) {
					return;
				}

				block.setType(Material.AIR);
				block.getLocation().getWorld().spawnParticle(Particle.BLOCK_CRACK, block.getLocation(), 1, 1, 1, 1);
				block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_WOOD_BREAK, 1, 1);
				block.getLocation().getWorld().createExplosion(block.getLocation().getX(), block.getLocation().getY(),
						block.getLocation().getZ(), 10, false, false);
			} else {
				if (!block.getType().equals(Material.WOOL)) {
					return;
				}

				block.setType(Material.AIR);
				blockLoc1.getBlock().setType(Material.AIR);
				blockLoc2.getBlock().setType(Material.AIR);
				blockLoc3.getBlock().setType(Material.AIR);
				block.getLocation().getWorld().spawnParticle(Particle.BLOCK_CRACK, block.getLocation(), 1, 1, 1, 1);
				block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_WOOD_BREAK, 1, 1);
				block.getLocation().getWorld().createExplosion(block.getLocation().getX(), block.getLocation().getY(),
						block.getLocation().getZ(), 10, false, false);
			}

		}
	}

	public void addParticleEffect(final Projectile entity) {
		new BukkitRunnable() {

			public void run() {
				if (!plg.getGameState().equals(GameState.GAMING)) {
					return;
				}

				if (arrows.contains(entity)) {//if the arrow still is in the air
					Map<Player, Location> locationCache = new HashMap<Player, Location>();
					for (Player online : Bukkit.getOnlinePlayers()) {
						locationCache.put(online, online.getLocation());

					}
					Location loc = entity.getLocation();
					PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.SPELL,
							true, (float) loc.getX(), (float) loc.getY(), (float) loc.getZ(),
							0, 0, 0,
							20, 1, null);

					for (Map.Entry<Player, Location> entry : locationCache.entrySet()) {
						if (entry.getKey().isOnline()) {

							//If the player has left when the arrow was going. we should remove him from the map to avoid NPE's
							if (entry.getValue().getWorld() == loc.getWorld() &&
									entry.getValue().distanceSquared(loc) <= 16 * 16) {
								((CraftPlayer) entry.getKey()).getHandle().playerConnection.sendPacket(packet);

							}

						}
					}
				} else {//we cancel the event when the arrow isn't in the air anymore.
					this.cancel();
					return;
				}
			}
		}.runTaskTimer(plg, 0, 1);
	}

}
