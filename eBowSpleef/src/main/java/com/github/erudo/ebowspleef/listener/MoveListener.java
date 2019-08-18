package com.github.erudo.ebowspleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.erudo.ebowspleef.Main;
import com.github.erudo.ebowspleef.enums.GameState;
import com.github.erudo.ebowspleef.enums.Teams;

public class MoveListener implements Listener {

	Main plg;

	public MoveListener(Main main) {
		this.plg = main;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (plg.getGameState().equals(GameState.PREPARE)) {
			if (plg.getTeam(Teams.BLUE).hasEntry(e.getPlayer().getName()) || plg.getTeam(Teams.RED).hasEntry(e.getPlayer().getName())) {
				e.setCancelled(true);
			}
		}
	}

}
