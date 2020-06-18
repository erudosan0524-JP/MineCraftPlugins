package com.github.erudosan0524jp.jikochu.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.github.erudosan0524jp.jikochu.Jikochu;

import net.md_5.bungee.api.ChatColor;

public class Counter extends BukkitRunnable {

	private final JavaPlugin plg;
	BukkitTask task;
	private Integer count;



	public Counter(JavaPlugin plg_, int count_) {
		plg = plg_;
		count = count_;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		if (count <= 0) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.sendTitle(ChatColor.RED + "自己中" + ChatColor.BLACK + "クラフト", ChatColor.GRAY + "START!!");
				plg.getServer().getScheduler().cancelTask(task.getTaskId());//自分自身を止める
				p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.5f, 1);
			}
			Jikochu.isGameStarting = true;


		} else {

			for(Player p : Bukkit.getOnlinePlayers()) {
				p.sendTitle(count.toString(), null);
				p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
			}

		}

		count--;
	}

	public void setTask(BukkitTask task) {
		this.task = task;
	}

}
