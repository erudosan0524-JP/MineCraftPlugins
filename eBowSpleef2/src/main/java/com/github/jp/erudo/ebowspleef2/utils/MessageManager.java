package com.github.jp.erudo.ebowspleef2.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class MessageManager {

	private static final String[] commandMessage = {
			"・/ebs start [時間]","ゲームを指定時間で開始します。","※時間を省略した場合はConfigに書いてある時間で開始します。",
			"・/ebs set","ランダムでチーム分けをします",
			"・/ebs setred [プレイヤー]","指定プレイヤーを赤チームに設定します。",
			"・/ebs setblue [プレイヤー]","指定プレイヤーを青チームに設定します。",
			"・/ebs setredpos","現在位置をレッドチームのスタート位置に設定します。",
			"・/ebs setbluepos","現在位置をブルーチームのスタート位置に設定します。",
			"・/ebs setlobbypos","現在位置をロビーのスポーン位置とします。"
			,"・/ebs version","このプラグインのバージョンを表示します。"
			};

	public static void sendMessage(Player player, String arg) {
		player.sendMessage(ChatColor.DARK_PURPLE + "【eBowSpleef】" + ChatColor.WHITE + arg);
	}

	public static void messageAll(String arg) {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.sendMessage(ChatColor.DARK_PURPLE + "【eBowSpleef】" + ChatColor.WHITE + arg);
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
