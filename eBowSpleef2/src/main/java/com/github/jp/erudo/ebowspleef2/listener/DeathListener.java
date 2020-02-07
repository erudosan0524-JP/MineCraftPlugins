package com.github.jp.erudo.ebowspleef2.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.enums.Teams;
import com.github.jp.erudo.ebowspleef2.utils.PlayersSetting;

public class DeathListener implements Listener {

	private com.github.jp.erudo.ebowspleef2.Main plg;

	public DeathListener(Main main) {
		this.plg = main;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player player = e.getEntity();

		if(plg.getMyConfig().isCanRespawn()) {
			if(plg.getTeam(Teams.BLUE).hasEntry(player.getName())) {
				plg.setRedPoint(1);
			}else if(plg.getTeam(Teams.RED).hasEntry(player.getName())) {
				plg.setBluePoint(1);
			}
		} else {
			player.setGameMode(GameMode.SPECTATOR);
			PlayersSetting.addPlayerToTeam(Teams.SPECTATOR, player);
		}
	}
}
