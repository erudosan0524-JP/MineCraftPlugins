package com.github.jp.erudo.ebowspleef2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Score;

import com.github.jp.erudo.ebowspleef2.enums.GameState;
import com.github.jp.erudo.ebowspleef2.enums.Teams;
import com.github.jp.erudo.ebowspleef2.utils.MessageManager;

public class Game extends BukkitRunnable {

	private final Main plg;
	private BukkitTask task;
	private int count;

	public Game(Main plg, int count) {
		this.plg = plg;
		this.count = count;
	}

	public void run() {
		if (plg.getCurrentGameState() == GameState.END) {
			MessageManager.broadcastMessage("試合終了！！");
			count = 0;
			for (Player player : Bukkit.getServer().getOnlinePlayers()) {
				player.setSneaking(false);

				if (player.getWorld().getPVP()) {
					continue;
				} else {
					player.getWorld().setPVP(false);
				}
			}
			plg.getServer().getScheduler().cancelTask(task.getTaskId());
		}

		if (plg.getCurrentGameState() == GameState.GAMING) {
			if (count > 0) {
				Score TimeScore = plg.getObj().getScore(ChatColor.GOLD + "残り時間: ");
				TimeScore.setScore(count);

				Score PlayersNumScore = plg.getObj().getScore(ChatColor.DARK_BLUE + "赤チーム人数: ");
				PlayersNumScore.setScore(plg.getTeam(Teams.RED).getEntries().size());

				Score OnisNumScore = plg.getObj().getScore(ChatColor.DARK_RED + "青チーム人数: ");
				OnisNumScore.setScore(+plg.getTeam(Teams.BLUE).getEntries().size());

				if (plg.getTeam(Teams.RED).getEntries().size() <= 0) {
					plg.setCurrentGameState(GameState.END);
					return;
				}

				if (plg.getTeam(Teams.BLUE).getEntries().size() <= 0) {
					plg.setCurrentGameState(GameState.END);
					return;
				}

			} else {
				plg.setCurrentGameState(GameState.END);
			}
			count--;
		}

	}

	public void setTask(BukkitTask task) {
		this.task = task;
	}

}
