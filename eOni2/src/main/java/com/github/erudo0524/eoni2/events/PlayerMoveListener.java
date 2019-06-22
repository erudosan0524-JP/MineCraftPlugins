package com.github.erudo0524.eoni2.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.erudo0524.eoni2.Main;
import com.github.erudo0524.eoni2.enums.GameState;

public class PlayerMoveListener implements Listener {

	private Main plg;

	public PlayerMoveListener(Main plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();

		//ゲーム中でなかったらreturn;
		if (!(plg.getCurrentGameState() == GameState.GAMING)) {
			return;
		}

		//ゲームモードがクリエだったらreturn;
		if (player.getGameMode().equals(GameMode.CREATIVE)) {
			return;
		}

		///////////////////////////////
		///		ここからが処理		///
		///////////////////////////////

		//TODO: ここがうまくいかない
		if(player.getLocation().getBlock().getRelative(BlockFace.SELF).isLiquid()) {
			Block block = player.getLocation().getBlock().getRelative(BlockFace.SELF);
			if(block.getType() == Material.WATER) {
				player.damage(0.1);
			}else if(block.getType() == Material.LAVA) {
				player.damage(100000000);
			}
		}
	}
}
