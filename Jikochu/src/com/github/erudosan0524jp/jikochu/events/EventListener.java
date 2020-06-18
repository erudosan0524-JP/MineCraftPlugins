package com.github.erudosan0524jp.jikochu.events;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.erudosan0524jp.jikochu.Jikochu;
import com.github.erudosan0524jp.jikochu.runnable.GameRunnable;
import com.github.erudosan0524jp.jikochu.utils.Config;
import com.github.erudosan0524jp.jikochu.utils.Datas;

public class EventListener implements Listener {

	JavaPlugin plg;

	public EventListener(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
		this.plg = plg;
	}

	@EventHandler
	public void onCreateTargetBlock(CraftItemEvent e) {
		if(Jikochu.isGameStarting) {
			Material item = e.getCurrentItem().getType();
			if(item == Material.DIAMOND_BLOCK) {
				for(Player p : Bukkit.getServer().getOnlinePlayers()) {
					p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 1, 1);
					p.spawnParticle(Particle.VILLAGER_HAPPY, p.getLocation(), 50, 10, 10, 10);
					p.sendMessage("目的達成！");
					Jikochu.isGameStarting = false;
				}
			}
		}
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if(Jikochu.isGameStarting) {
			if(!(e.getEntity() instanceof Player)) {
				return;
			}

			Player player = (Player) e.getEntity();

			if(player == Datas.E_Player) {
				Jikochu.isGameStarting = false;
				plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(), "stop");
			} else {
				if(GameRunnable.Warningcount.get(player) >= Config.getInstance(plg).getWarningCount()) {
					player.kickPlayer(ChatColor.RED + "私に従わなかった罰だ！");
					Bukkit.getBanList(Type.NAME).addBan(player.getName(), ChatColor.RED + "自己中な人に従わなかった", null, null);
					GameRunnable.Warningcount.put(player, 0);
				}
			}
		}
	}

}
