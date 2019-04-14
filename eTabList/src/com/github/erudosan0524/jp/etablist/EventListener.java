package com.github.erudosan0524.jp.etablist;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EventListener implements Listener {

	JavaPlugin plg;

	public EventListener(JavaPlugin plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		changeTabName(player);
	}

	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent e) {
		Player player = e.getPlayer();
		changeTabName(player);
	}

	private void changeTabName(Player player) {
		World world = player.getWorld();
		Config conf = new Config(plg, world);

		if(plg.getConfig().isSet(world.getName())) {
			player.setPlayerListName(conf.getColor() +  "【" + world.getName() +  "】"  + ChatColor.RESET + player.getName());
		}else {
			player.setPlayerListName(ChatColor.WHITE + "【" + world.getName() +  "】"  + ChatColor.RESET + player.getName());
		}
	}

}
