package com.github.jp.erudo.eanticheat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.jp.erudo.eanticheat.Main;
import com.github.jp.erudo.eanticheat.checks.CheckResult;
import com.github.jp.erudo.eanticheat.checks.movements.Speed;
import com.github.jp.erudo.eanticheat.utils.Distance;
import com.github.jp.erudo.eanticheat.utils.User;

public class PlayerMoveListener implements Listener {

	public PlayerMoveListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}


	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		User u = Main.USERS.get(player.getUniqueId());

		//Speedの検知
		Distance d = new Distance(e);
		CheckResult speed = Speed.runCheck(d,u);
		if(speed.failed()) {
			//プレイヤーをtoまでTP
			e.setTo(e.getFrom());
			Main.log(speed,u);
		}


	}

}
