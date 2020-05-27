package com.github.jp.erudo.eanticheat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.jp.erudo.eanticheat.Main;
import com.github.jp.erudo.eanticheat.checks.CheckResult;
import com.github.jp.erudo.eanticheat.checks.movements.NoSlowDown;
import com.github.jp.erudo.eanticheat.checks.movements.Speed;
import com.github.jp.erudo.eanticheat.utils.Distance;
import com.github.jp.erudo.eanticheat.utils.MessageManager;
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
			MessageManager.log(speed,u);
		}


		//NoSlowDown 動き始めたらカウントを増やす→食べる時の足の速さを図るため
		NoSlowDown.registerMove(d,u);
		CheckResult noslow = NoSlowDown.runCheck(d, u);
		if(noslow.failed()) {
			e.setTo(e.getFrom());
			MessageManager.log(noslow,u);
		}


	}

}
