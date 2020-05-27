package com.github.jp.erudo.eanticheat.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.github.jp.erudo.eanticheat.Main;
import com.github.jp.erudo.eanticheat.checks.CheckResult;

public class MessageManager {

	//pom.xmlに書かれたnameプロパティを取得してprefixに代入
	public static final String prefix = ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "[" + Main.pluginName + "]  " + ChatColor.WHITE;


	public static void log(CheckResult cr, User u) {
		String message = MessageManager.prefix + u.getPlayer().getName() + " " + ChatColor.GRAY + cr.getType() + "; " + cr.getMessage() + ChatColor.RED + "【" + cr.getLevel() + "】";
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasPermission(Settings.ALERT)) {
				p.sendMessage(message);
			}
		}
	}

}
