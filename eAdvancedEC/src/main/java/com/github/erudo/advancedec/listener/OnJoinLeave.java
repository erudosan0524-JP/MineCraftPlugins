package com.github.erudo.advancedec.listener;

import java.util.Objects;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.erudo.advancedec.Main;

public class OnJoinLeave implements Listener {

	private Main plg;

	public OnJoinLeave(Main main) {
		this.plg = main;
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();

		if(Objects.nonNull(plg.getContent(player.getUniqueId()))) {
			if(!Main.inventories.containsKey(player.getUniqueId())) {
				Main.players.add(player.getUniqueId());
				Main.inventories.put(player.getUniqueId(), plg.getContent(player.getUniqueId()));
			}
		}
	}
}
