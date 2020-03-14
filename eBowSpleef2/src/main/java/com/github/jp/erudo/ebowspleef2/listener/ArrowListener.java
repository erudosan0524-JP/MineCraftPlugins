package com.github.jp.erudo.ebowspleef2.listener;

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
import org.bukkit.util.Vector;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.enums.GameState;
import com.github.jp.erudo.ebowspleef2.item.Items;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;

public class ArrowListener implements Listener {
	public List<Projectile> arrows = new ArrayList<Projectile>();

	private Main plg;

	public ArrowListener(Main main) {
		this.plg = main;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onShoot(EntityShootBowEvent e) {
		if (!plg.getCurrentGameState().equals(GameState.GAMING)) {
			return;
		}

		if(!(e.getEntity() instanceof Player)) {
			return;
		}

		Player player = (Player) e.getEntity();

		if(player.getInventory().getItemInMainHand().getType() == Material.BOW) {
			if(player.getInventory().getItemInHand().hasItemMeta()){

				String str = ChatColor.stripColor(player.getInventory().getItemInHand().getItemMeta().getDisplayName());

				if(str.equals(ChatColor.stripColor(Items.bow3Name))) {
					double multiply = e.getProjectile().getVelocity().length();

					Location loc = player.getLocation();

					double arrowAngle = 15; //15度

					double totalAngle1 = (((loc.getYaw()+90) + arrowAngle) * Math.PI)/180;
					double arrowDirX1 = Math.cos(totalAngle1);
					double arrowDirZ1 =  Math.sin(totalAngle1);

					double totalAngle2 = (((loc.getYaw()+90) - arrowAngle) * Math.PI)/180;
					double arrowDirX2 = Math.cos(totalAngle2);
					double arrowDirZ2 = Math.sin(totalAngle2);

					Vector arrowDir1 = new Vector(arrowDirX1,loc.getDirection().getY(),arrowDirZ1).normalize().multiply(multiply);
					Vector arrowDir2 = new Vector(arrowDirX2,loc.getDirection().getY(),arrowDirZ2).normalize().multiply(multiply);

					player.launchProjectile(Arrow.class,arrowDir1);
					player.launchProjectile(Arrow.class,arrowDir2);
				}
			}
		}

		//矢の軌道
		arrows.add((Projectile) e.getProjectile());
		addParticleEffect((Projectile) e.getProjectile());
	}

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if (!plg.getCurrentGameState().equals(GameState.GAMING)) {
			return;
		}

		if (e.getEntity() instanceof Arrow) {
			arrows.remove(e.getEntity());

			//プレイヤーに当たったら
			if (e.getHitEntity() != null) {
				if (e.getHitEntity() instanceof Player) {
					Player player = (Player) e.getHitEntity();
					player.damage(2);
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 5 * 20, 1));
					player.getLocation().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, player.getLocation(), 50, 5,
							3, 5);
					player.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_PLACE,
							(float) 0.5, 5);
					return;
				}
				return;
			}

			//あたったブロックが羊毛だったら消去
			//あたったブロックを含む2*2*2の立方体

			Block block = e.getHitBlock();
			Location blockLoc1 = new Location(block.getWorld(), block.getLocation().getX() + 1,
						block.getLocation().getY(),
						block.getLocation().getZ());
			Location blockLoc2 = new Location(block.getWorld(), block.getLocation().getX(), block.getLocation().getY(),
						block.getLocation().getZ() + 1);
			Location blockLoc3 = new Location(block.getWorld(), block.getLocation().getX() + 1,
						block.getLocation().getY(),
						block.getLocation().getZ() + 1);
			Location blockLoc4 = new Location(block.getWorld(), block.getLocation().getX(),
					block.getLocation().getY()+1,
					block.getLocation().getZ());
			Location blockLoc5 = new Location(block.getWorld(), block.getLocation().getX()+1,
					block.getLocation().getY()+1,
					block.getLocation().getZ());
			Location blockLoc6 = new Location(block.getWorld(), block.getLocation().getX(),
					block.getLocation().getY()+1,
					block.getLocation().getZ()+1);
			Location blockLoc7 = new Location(block.getWorld(), block.getLocation().getX()+1,
					block.getLocation().getY()+1,
					block.getLocation().getZ()+1);


			if (plg.getMyConfig().getArrowrange() <= 1) {
				if (!(block.getType() == Material.WOOL)
						|| !(blockLoc1.getBlock().getType() == Material.WOOL)
						|| !(blockLoc2.getBlock().getType() == Material.WOOL)
						|| !(blockLoc3.getBlock().getType() == Material.WOOL)
						|| !(blockLoc4.getBlock().getType() == Material.WOOL)
						|| !(blockLoc5.getBlock().getType() == Material.WOOL)
						|| !(blockLoc6.getBlock().getType() == Material.WOOL)
						|| !(blockLoc7.getBlock().getType() == Material.WOOL)) {
					return;
				}

				block.setType(Material.AIR);
				block.getLocation().getWorld().spawnParticle(Particle.BLOCK_CRACK, block.getLocation(), 1, 1, 1, 1);
				blockLoc1.getWorld().spawnParticle(Particle.BLOCK_CRACK, block.getLocation(), 3, 2, 2, 2);
				blockLoc2.getWorld().spawnParticle(Particle.BLOCK_CRACK, block.getLocation(), 3, 2, 2, 2);
				blockLoc3.getWorld().spawnParticle(Particle.BLOCK_CRACK, block.getLocation(), 3, 2, 2, 2);
				block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_WOOD_BREAK, 1, 1);
				block.getLocation().getWorld().createExplosion(block.getLocation().getX(), block.getLocation().getY(),
						block.getLocation().getZ(), 10, false, false);
			} else {
				if (!(block.getType() == Material.WOOL)
						|| !(blockLoc1.getBlock().getType() == Material.WOOL)
						|| !(blockLoc2.getBlock().getType() == Material.WOOL)
						|| !(blockLoc3.getBlock().getType() == Material.WOOL)
						|| !(blockLoc4.getBlock().getType() == Material.WOOL)
						|| !(blockLoc5.getBlock().getType() == Material.WOOL)
						|| !(blockLoc6.getBlock().getType() == Material.WOOL)
						|| !(blockLoc7.getBlock().getType() == Material.WOOL)) {
					return;
				}

				block.setType(Material.AIR);
				blockLoc1.getBlock().setType(Material.AIR);
				blockLoc2.getBlock().setType(Material.AIR);
				blockLoc3.getBlock().setType(Material.AIR);
				blockLoc4.getBlock().setType(Material.AIR);
				blockLoc5.getBlock().setType(Material.AIR);
				blockLoc6.getBlock().setType(Material.AIR);
				blockLoc7.getBlock().setType(Material.AIR);
				block.getLocation().getWorld().spawnParticle(Particle.BLOCK_CRACK, block.getLocation(), 3, 2, 2, 2);
				blockLoc1.getWorld().spawnParticle(Particle.BLOCK_CRACK, block.getLocation(), 3, 2, 2, 2);
				blockLoc2.getWorld().spawnParticle(Particle.BLOCK_CRACK, block.getLocation(), 3, 2, 2, 2);
				blockLoc3.getWorld().spawnParticle(Particle.BLOCK_CRACK, block.getLocation(), 3, 2, 2, 2);
				block.getLocation().getWorld().playSound(block.getLocation(), Sound.BLOCK_WOOD_BREAK, 1, 1);
				block.getLocation().getWorld().createExplosion(block.getLocation().getX(), block.getLocation().getY(),
						block.getLocation().getZ(), 10, false, false);
			}

		}
	}

	public void addParticleEffect(final Projectile entity) {
		new BukkitRunnable() {

			public void run() {
				if (!plg.getCurrentGameState().equals(GameState.GAMING)) {
					return;
				}

				if (arrows.contains(entity)) {//矢が空中にあったら
					Map<Player, Location> locationCache = new HashMap<Player, Location>();
					for (Player online : Bukkit.getOnlinePlayers()) {
						locationCache.put(online, online.getLocation());

					}
					Location loc = entity.getLocation();
					PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.HEART, 	//パーティクルの種類
							true, 		//true
							(float) loc.getX(), 	//位置X
							(float) loc.getY(), 	//位置Y
							(float) loc.getZ(),		//位置Z
							0,				//x方面の拡散offset
							0, 				//y方面の拡散
							0,				//z方面の拡散
							20, 			//パーティクル出現スピード(tick) 20tick=1s
							10, 				//パーティクルの量
							null);

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
