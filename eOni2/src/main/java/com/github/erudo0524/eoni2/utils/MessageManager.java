package com.github.erudo0524.eoni2.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class MessageManager {

	private static final String[] commandMessage = {
			"・/eoni start [時間]","ゲームを指定時間で開始します。","※時間を省略した場合はConfigに書いてある時間で開始します。",
			"・/eoni wp [プレイヤー名]","指定プレイヤーを鬼としてワープします。","※プレイヤー名を省略した場合ランダムでワープします。",
			"・/eoni settppos","現在位置をプレイヤーのTP位置に設定します。",
			"・/eoni setonipos","鬼のTP位置を設定します",
			"・/eoni setblockpos","開始時に削除されるブロックを設定します。"
			,"・/eoni version","このプラグインのバージョンを表示します。"
			};

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

	public static void CommandContent(Player player) {
		player.sendMessage(ChatColor.AQUA + "【eOni2】" + ChatColor.WHITE + "コマンドの使い方");
		for(int i=0; i < commandMessage.length; i++) {
			player.sendMessage(commandMessage[i]);
		}
	}
}
