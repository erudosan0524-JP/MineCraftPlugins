package com.github.erudo0524.eoni2.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scoreboard.Team;

import com.github.erudo0524.eoni2.Main;
import com.github.erudo0524.eoni2.MessageManager;
import com.github.erudo0524.eoni2.enums.GameState;
import com.github.erudo0524.eoni2.enums.Teams;

public class DamagePlayerListener implements Listener {

	private Main plg;

	public DamagePlayerListener(Main plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onDamagePlayer(EntityDamageByEntityEvent e) {
		//プレイヤーからプレイヤーの攻撃でない場合はreturn;
		if(!(e.getEntity() instanceof Player) && !(e.getDamager() instanceof Player)) {
			return;
		}

		Player entity = (Player) e.getEntity();
		Player damager = (Player) e.getDamager();

		//ゲームモードがクリエだったらreturn;
		if(entity.getGameMode().equals(GameMode.CREATIVE) || damager.getGameMode().equals(GameMode.CREATIVE)) {
			return;
		}

		//ゲーム中でなかったらreturn;
		if(!(plg.getCurrentGameState() == GameState.GAMING)) {
			return;
		}

		//プレイヤーがチームに所属していなかったらreturn;
		for(Team team : entity.getScoreboard().getTeams()) {
			if(!(team.getName() == Teams.ONI.getName()) || !(team.getName() == Teams.PLAYER.getName())) {
				return;
			}

			//entityとdamagerのチームが同じだったらreturn;
			for(Team team2 : damager.getScoreboard().getTeams()) {
				if(team == team2) {
					return;
				}
			}
		}


		///////////////////////////////
		///		ここからが処理		///
		///////////////////////////////

		entity.teleport(plg.getTpPos());
		MessageManager.ArrestMessage(entity, damager);
		plg.removePlayerFromTeam(Teams.PLAYER, entity);
		plg.addPlayerToTeam(Teams.ONI, entity);



	}

}
