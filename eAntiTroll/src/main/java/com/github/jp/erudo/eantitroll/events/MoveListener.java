package com.github.jp.erudo.eantitroll.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.jp.erudo.eantitroll.utils.User;

public class MoveListener implements Listener {

	public MoveListener(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();

		if(User.isFreezing(player.getUniqueId())) {
			player.teleport(player);
		}
	}
}
