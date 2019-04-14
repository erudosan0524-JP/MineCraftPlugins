package com.github.erudosan05242.jp.edisablespawnegg;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EDisableSpawnEgg extends JavaPlugin implements Listener {

	@Override
	public void onDisable() {
		getLogger().info("プラグインが無効になりました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました");
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(player.getItemInHand().getType() == Material.MONSTER_EGG || player.getItemInHand().getType() == Material.MONSTER_EGGS) {
				e.setCancelled(true);
			}
		}
	}

}
