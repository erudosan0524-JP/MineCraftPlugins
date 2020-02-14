package com.github.jp.erudo.ebowspleef2.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.enums.GameState;
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
		if(!(plg.getCurrentGameState() == GameState.GAMING)) {
			return;
		}

		Player player = e.getEntity();

		if(plg.getMyConfig().isCanRespawn()) {
			//リスポーン可能ならポイント増やすだけ
			if(plg.getTeam(Teams.BLUE).hasEntry(player.getName())) {
				plg.addRedPoint();
			}else if(plg.getTeam(Teams.RED).hasEntry(player.getName())) {
				plg.addBluePoint();
			}
		} else {
			//リスポーン不可ならチームから抜ける
			player.setGameMode(GameMode.SPECTATOR);
			PlayersSetting.addPlayerToTeam(Teams.SPECTATOR, player);
			player.setSneaking(false);
			if(plg.getTeam(Teams.BLUE).hasEntry(player.getName())) {
				plg.getTeam(Teams.BLUE).removeEntry(player.getName());
				plg.setRedPoint(1);
			}else if(plg.getTeam(Teams.RED).hasEntry(player.getName())) {
				plg.getTeam(Teams.RED).removeEntry(player.getName());
				plg.setBluePoint(1);
			}
		}
	}
}
