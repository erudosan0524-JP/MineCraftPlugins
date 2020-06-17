package com.github.jp.erudo.eanticheat.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import com.github.jp.erudo.eanticheat.Main;

public class InventoryListener implements Listener {

	public InventoryListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onItemSwitch(PlayerItemHeldEvent e) {
		Main.getUser(e.getPlayer()).setBow(false);
	}

}
