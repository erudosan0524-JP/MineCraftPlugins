package com.github.erudo.ebowspleef.runnable;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.github.erudo.ebowspleef.Main;
import com.github.erudo.ebowspleef.utils.TitleSender;

public class CountDown extends BukkitRunnable {

	private final Main plg;
	private BukkitTask task;
	private int count;
	private int time;
	private TitleSender titleSender;

	public CountDown(Main _plg, int _count, int time) {
		this.plg = _plg;
		this.count = _count;
		this.time = time;
		titleSender = new TitleSender();
	}

	public void run() {

		if (count > 0) {
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				titleSender.sendTitle(p, String.valueOf(count), null, null);
				p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
			}
		} else {
			count = 0;
			plg.getServer().getScheduler().cancelTask(task.getTaskId());
			plg.GameStart(time);
		}
		count--;

	}

	public void setTask(BukkitTask task) {
		this.task = task;
	}
}
