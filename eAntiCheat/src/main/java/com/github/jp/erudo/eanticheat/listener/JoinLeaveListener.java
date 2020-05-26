package com.github.jp.erudo.eanticheat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.jp.erudo.eanticheat.Main;
import com.github.jp.erudo.eanticheat.utils.User;

public class JoinLeaveListener implements Listener {

	public JoinLeaveListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		Main.USERS.put(player.getUniqueId(), new User(player));
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		Main.USERS.remove(player.getUniqueId());
	}
}
