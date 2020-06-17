package com.github.jp.erudo.eanticheat.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.github.jp.erudo.eanticheat.Main;
import com.github.jp.erudo.eanticheat.checks.CheckResult;
import com.github.jp.erudo.eanticheat.checks.CheckType;
import com.github.jp.erudo.eanticheat.checks.Level;
import com.github.jp.erudo.eanticheat.checks.player.FastUse;
import com.github.jp.erudo.eanticheat.utils.MessageManager;
import com.github.jp.erudo.eanticheat.utils.Settings;
import com.github.jp.erudo.eanticheat.utils.User;

public class FoodLevelListener implements Listener {

	public FoodLevelListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@SuppressWarnings("deprecation")
	//NoSlowDown用 フードレベルが歩いてる途中に変わったら
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		if(!(e.getEntity() instanceof Player)) return;


		Player p = (Player) e.getEntity();

		User u = Main.getUser(p);

		if(!u.getPlayer().isOnGround()) return;

		if(u.getPlayer().getItemInHand() != null && Settings.FOODS.contains(u.getPlayer().getItemInHand().getType()) && e.getFoodLevel() > u.getCurrentFoodLevel()) {
			if(u.getInvalidFoodEatableCount() != 0) {
				e.setCancelled(true);
				u.getPlayer().teleport(u.getFoodStartLoc());
				MessageManager.log(new CheckResult(Level.DEFINITELY,u.getInvalidFoodEatableCount() + "times in a row. max=(0)",CheckType.NOSLOW), u);
			}
		}


		CheckResult result = FastUse.runFoodCheck(u);
		if(result.failed()) {
			e.setCancelled(true);
			MessageManager.log(result, u);
		}
	}
}
