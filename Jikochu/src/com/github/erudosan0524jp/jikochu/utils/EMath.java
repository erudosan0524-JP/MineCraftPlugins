package com.github.erudosan0524jp.jikochu.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;

public class EMath {

	JavaPlugin plg;
	int amount;
	BukkitTask task;

	public EMath(JavaPlugin plg, int amount) {
		this.plg = plg;
		this.amount = amount;
	}

	//	public static ArrayList<Location> getCircle(Location center, double radius, int amount)
	//    {
	//        World world = center.getWorld();
	//        double increment = (2 * Math.PI) / amount;
	//        ArrayList<Location> locations = new ArrayList<Location>();
	//        for(int i = 0;i < amount; i++)
	//        {
	//            double angle = i * increment;
	//            double x = center.getX() + (radius * Math.cos(angle));
	//            double z = center.getZ() + (radius * Math.sin(angle));
	//            locations.add(new Location(world, x, center.getY(), z));
	//        }
	//        return locations;
	//    }

	public static void drawCircle(Location center, int radius) {
		for (double t = 0; t < 100; t += 0.5) {
			float x = radius * (float) Math.sin(t);
			float z = radius * (float) Math.cos(t);
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FLAME, true,
					(float) center.getX() + x, (float) center.getY(), (float) center.getZ() + z, 0, 0, 0, 0, 1);
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
			}
		}
	}

	public void drawSphere(Location center, int radius) {
		for (double t = 0; t < 100; t += 0.5) {

			float x = (float) (radius * Math.sin(t));
			float y = (float) (radius * Math.cos(Math.PI) + 1.5);
			float z = (float) (radius * Math.cos(t));

			new BukkitRunnable() {

				@Override
				public void run() {
					if (amount <= 0) {
						plg.getServer().getScheduler().cancelTask(task.getTaskId());//自分自身を止める
					} else {
						PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FLAME, true,
								(float) center.getX() + x, (float) center.getY() + y, (float) center.getZ() + z, 0, 0, 0, 0,1);
						for (Player p : Bukkit.getServer().getOnlinePlayers()) {
							((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
						}
					}
					amount--;
				}

			}.runTaskTimer(plg, 0L, 20L);

		}
	}

	public void setTask(BukkitTask task) {
		this.task = task;
	}
}
