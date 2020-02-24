package jp.github.erudo.nowater.events;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class OnPlace implements Listener{

	Material bucket = null;
	Player player = null;
	Material block = null;

	public OnPlace(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void placeWaterbyBucket(PlayerBucketEmptyEvent event) {
		bucket = event.getBucket();
		player = event.getPlayer();

		if (player.isOp() || player.hasPermission("nowater.admin")) {
			return;
		}else if(player.getGameMode() == GameMode.SURVIVAL) {
			return;
		}

		if(bucket == Material.WATER_BUCKET) {

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
		}else if(player.getGameMode() == GameMode.SURVIVAL) {
			return;
		}

		if(block == Material.WATER || block == Material.WATER_LILY || block == Material.STATIONARY_WATER) {

			e.setCancelled(true);

			Bukkit.broadcastMessage("§4" + player.getName() + "が水を置こうとしています！！");

		}




	}
}
