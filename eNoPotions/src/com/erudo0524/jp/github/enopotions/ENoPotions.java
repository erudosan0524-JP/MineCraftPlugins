package com.erudo0524.jp.github.enopotions;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

public class ENoPotions extends JavaPlugin {

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");

	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました");

		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getServer().getOnlinePlayers()) {
                	if(p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                		if(p.isOp() || p.hasPermission("enopotions.admin")) {
                			return;
                		}

                		p.removePotionEffect(PotionEffectType.INVISIBILITY);
                		Bukkit.broadcastMessage(p.getName() + "から" + ChatColor.LIGHT_PURPLE + "透明化効果" + ChatColor.WHITE + "を消去しました");
                	}

                }

            }
        }, 0L, 20L);
	}


}
