package com.github.erudo0524.eoni2.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class MessageManager {

	public static void sendMessage(Player player, String arg) {
		player.sendMessage(ChatColor.AQUA + "【eOni2】" + ChatColor.WHITE + arg);
	}

	public static void messageAll(String arg) {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.sendMessage(ChatColor.AQUA + "【eOni2】" + ChatColor.WHITE + arg);
		}
	}

	public static void broadcastMessage(String arg) {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.sendMessage(ChatColor.BOLD + "" + ChatColor.AQUA + arg);
		}
	}

	//確保メッセージ
	public static void ArrestMessage(Player player, Player hunter) {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.sendMessage(ChatColor.RED + player.getName() + "は" + hunter.getName() + "に捕まった！");
		}
	}
}
