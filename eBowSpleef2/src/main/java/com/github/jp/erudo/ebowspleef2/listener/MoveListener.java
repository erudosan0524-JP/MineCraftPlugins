package com.github.jp.erudo.ebowspleef2.listener;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.enums.GameState;

public class MoveListener implements Listener {

	private Main plg;

	public MoveListener(Main plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(!(plg.getCurrentGameState() == GameState.GAMING)) {
			return;
		}

		Player player = e.getPlayer();
		Block downBlock = player.getLocation().getBlock().getRelative(BlockFace.DOWN);

		if(downBlock.getType() == Material.CONCRETE) {
			if(downBlock.getData() == 14 || downBlock.getData() == 11) { //赤コンクリートまたは青コンクリート
				player.damage(1);
			}
		}
	}

}
