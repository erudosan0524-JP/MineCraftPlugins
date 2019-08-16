package com.github.erudo0524.eoni2.events;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
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
		final Player player = e.getPlayer();

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
		Block getToBlock = e.getTo().getBlock();
		if (getToBlock.isLiquid()) {
			player.damage(0.1);

		}
	}
}
