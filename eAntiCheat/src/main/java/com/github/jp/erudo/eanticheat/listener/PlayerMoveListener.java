package com.github.jp.erudo.eanticheat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.jp.erudo.eanticheat.Main;
import com.github.jp.erudo.eanticheat.checks.Speed;

public class PlayerMoveListener implements Listener {

	public PlayerMoveListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}


	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();

		//Speedの検知
		Speed speed = new Speed(player);
		speed.check();

	}

}
