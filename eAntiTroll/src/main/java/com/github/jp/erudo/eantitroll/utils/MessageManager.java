package com.github.jp.erudo.eantitroll.utils;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class MessageManager {

	public static void sendHoverText(Player p, String text, String hoverText, String command) {
		HoverEvent hoverEvent = null;
		if (hoverText != null) {
			BaseComponent[] hover = new ComponentBuilder(hoverText).create();
			hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, hover);
		}

		ClickEvent clickEvent = null;
		if (command != null) {
			clickEvent = new ClickEvent(Action.RUN_COMMAND, command);
		}

		BaseComponent[] message = new ComponentBuilder(text).event(hoverEvent).event(clickEvent).create();
		p.spigot().sendMessage(message);
	}

	public static void sendBANMessage(Player player) {
		player.kickPlayer("【eAntiTroll】" + ChatColor.RED + "BAN： " + ChatColor.GOLD + "荒らしの疑い" + ChatColor.GREEN + "BAN期間： 無期限");
		Bukkit.getBanList(Type.NAME).addBan(player.getName(), ChatColor.RED + "荒らしの疑い", null, null);
		Bukkit.getOnlinePlayers().forEach(p_ -> p_.sendMessage(ChatColor.GOLD + player.getName() + "が荒らしの疑いでBANされました"));

	}

}
