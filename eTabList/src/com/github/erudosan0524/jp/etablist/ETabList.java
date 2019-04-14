package com.github.erudosan0524.jp.etablist;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_12_R1.ChatComponentText;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;

public class ETabList extends JavaPlugin {

	public List<ChatColor> rainbow_color = new ArrayList<>();


	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");;
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました");
		new EventListener(this);

		rainbow_color.add(ChatColor.AQUA);
		rainbow_color.add(ChatColor.BLACK);
		rainbow_color.add(ChatColor.BLUE);
		rainbow_color.add(ChatColor.DARK_AQUA);
		rainbow_color.add(ChatColor.DARK_BLUE);
		rainbow_color.add(ChatColor.DARK_GRAY);
		rainbow_color.add(ChatColor.DARK_GREEN);
		rainbow_color.add(ChatColor.DARK_PURPLE);
		rainbow_color.add(ChatColor.DARK_RED);
		rainbow_color.add(ChatColor.GOLD);
		rainbow_color.add(ChatColor.GRAY);
		rainbow_color.add(ChatColor.GREEN);
		rainbow_color.add(ChatColor.LIGHT_PURPLE);
		rainbow_color.add(ChatColor.RED);
		rainbow_color.add(ChatColor.WHITE);
		rainbow_color.add(ChatColor.YELLOW);


		new BukkitRunnable() {

			@Override
			public void run() {
				PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
				try {

					Field a = packet.getClass().getDeclaredField("a");
					a.setAccessible(true);
					Field b = packet.getClass().getDeclaredField("b");
					b.setAccessible(true);

					Object header1 = new ChatComponentText(rainbow_color.get((new Random()).nextInt(rainbow_color.size())) + "アルトサーバー");
					Object footer = new ChatComponentText(ChatColor.AQUA + "参加人数: " + ChatColor.WHITE + Bukkit.getServer().getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers());


					a.set(packet, header1);
					b.set(packet, footer);

					if(Bukkit.getOnlinePlayers().size() == 0) return;

					for(Player player : Bukkit.getOnlinePlayers()) {
						((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
					}

				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				;
			}

		}.runTaskTimer(this, 0, 20);
	}


}
