package com.github.jp.erudo.eanticheat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import com.github.jp.erudo.eanticheat.Main;
import com.github.jp.erudo.eanticheat.checks.CheckResult;
import com.github.jp.erudo.eanticheat.checks.player.FastUse;
import com.github.jp.erudo.eanticheat.utils.MessageManager;
import com.github.jp.erudo.eanticheat.utils.User;

public class BowListener implements Listener {

	public BowListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onShoot(ProjectileLaunchEvent e) {
		Player p = (Player) e.getEntity().getShooter();

		User u = Main.getUser(p);


		CheckResult result = FastUse.runBowCheck(u);
		if(result.failed()) {
			e.setCancelled(true);
			MessageManager.log(result, u);
		}
	}
}
