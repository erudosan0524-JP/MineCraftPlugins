package com.github.jp.erudo.eanticheat.utils;

import org.bukkit.ChatColor;

import com.github.jp.erudo.eanticheat.Main;

public class MessageManager {

	//pom.xmlに書かれたnameプロパティを取得してprefixに代入
	public static final String prefix = ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "[" + Main.pluginName + "]  " + ChatColor.GRAY;
}
