package com.github.jp.erudo.enowater.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

import com.github.jp.erudo.enowater.Main;

//水が床に置かれたときのリスナー
public class WaterPlaceListener implements Listener {

	Material bucket = null;
	Player player = null;
	Material block = null;

	public WaterPlaceListener(Main plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void placeWaterbyBucket(PlayerBucketEmptyEvent event) {
		bucket = event.getBucket();
		player = event.getPlayer();

		if (player.isOp() || player.hasPermission("nowater.admin")) {
			return;
		} else if (player.getGameMode() == GameMode.SURVIVAL) {
			return;
		}

		if (bucket == Material.WATER_BUCKET
				|| bucket == Material.COD_BUCKET
				|| bucket == Material.PUFFERFISH_BUCKET
				|| bucket == Material.SALMON_BUCKET
				|| bucket == Material.TROPICAL_FISH_BUCKET
				) {

			event.setCancelled(true);

			Bukkit.broadcastMessage("§4" + player.getName() + "が水を置こうとしています！！");
		}
	}

	@EventHandler
	public void placeWater(BlockPlaceEvent e) {
		block = e.getBlock().getType();
		player = e.getPlayer();

		if (player.isOp() || player.hasPermission("nowater.admin")) {
			return;
		} else if (player.getGameMode() == GameMode.SURVIVAL) {
			return;
		}

		if (block == Material.WATER) {

			e.setCancelled(true);

			Bukkit.broadcastMessage("§4" + player.getName() + "が水を置こうとしています！！");

		}

	}
}
