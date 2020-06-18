package com.github.jp.erudo.eanticheat.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.jp.erudo.eanticheat.Main;
import com.github.jp.erudo.eanticheat.utils.Settings;
import com.github.jp.erudo.eanticheat.utils.User;

public class PlayerInteractListener implements Listener {

	public PlayerInteractListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@SuppressWarnings("deprecation")
	//NoslowDown 食べ物を右クリックしたときに検知開始
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		User u = Main.getUser(e.getPlayer());

		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer().getItemInHand() != null) {
			if(Settings.FOODS.contains(e.getPlayer().getItemInHand().getType())) {
				u.setFoodStart();
				u.resetInvalidFoodEatableCount();

			}

			if(e.getPlayer().getItemInHand().getType() == Material.BOW && e.getPlayer().getInventory().contains(Material.ARROW)) {
				u.setBowStart(System.currentTimeMillis());
				u.setBow(true);
			}
		}
	}
}
