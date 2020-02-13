package com.github.jp.erudo.ebowspleef2.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.enums.GameState;
import com.github.jp.erudo.ebowspleef2.enums.Teams;
import com.github.jp.erudo.ebowspleef2.utils.PlayersSetting;

public class RespawnListener implements Listener {

	private Main plg;

	public RespawnListener(Main plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		if(!(plg.getCurrentGameState() == GameState.GAMING)) {
			return;
		}

		Player player = e.getPlayer();

		if(plg.getMyConfig().isCanRespawn()) {
			if (plg.getTeam(Teams.BLUE).hasEntry(player.getName())) {
				//テレポート＆スポーン地点設定
				e.setRespawnLocation(PlayersSetting.getBluePos());
				player.setGameMode(GameMode.ADVENTURE);
				player.setSneaking(true);
			//赤チームだったら
			} else if (plg.getTeam(Teams.RED).hasEntry(player.getName())) {
				e.setRespawnLocation(PlayersSetting.getRedPos());
				player.setGameMode(GameMode.ADVENTURE);
				player.setSneaking(true);
			}
		}
	}
}
