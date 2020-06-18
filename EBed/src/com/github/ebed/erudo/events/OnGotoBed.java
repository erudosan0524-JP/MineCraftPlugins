package com.github.ebed.erudo.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ebed.erudo.Utils;

public class OnGotoBed implements Listener {

	public OnGotoBed(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onGotoBed(PlayerBedEnterEvent e) {
		Player player = e.getPlayer();
		Utils.removePlayer(player);
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(ChatColor.GREEN + player.getName() + "が寝ました");
		}
	}
}
