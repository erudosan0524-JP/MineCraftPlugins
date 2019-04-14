package com.github.erudosan0524.jp.eloginmessage;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EventListener implements Listener {

	JavaPlugin plg;

	public EventListener(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
		this.plg = plg;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Config config = new Config(plg);

		e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + "がログインしました");
		for (String s : config.getLoginMessages()) {
			e.getPlayer().sendMessage(s);
		}

	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.YELLOW + e.getPlayer().getName() + "がログアウトしました");
	}
}
