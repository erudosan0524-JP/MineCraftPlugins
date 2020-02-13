package com.github.jp.erudo.ebowspleef2.runnable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Score;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.enums.GameState;
import com.github.jp.erudo.ebowspleef2.enums.Teams;
import com.github.jp.erudo.ebowspleef2.utils.MessageManager;
import com.github.jp.erudo.ebowspleef2.utils.PlayersSetting;
import com.github.jp.erudo.ebowspleef2.utils.TitleSender;

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

			plg.setBluePoint(0);
			plg.setRedPoint(0);

			for (Player player : Bukkit.getServer().getOnlinePlayers()) {
				player.setSneaking(false);
				player.teleport(PlayersSetting.getLobbyPos());
				player.setGameMode(GameMode.ADVENTURE);

				if (plg.getTeam(Teams.BLUE).hasEntry(player.getName())) {
					plg.getTeam(Teams.BLUE).removeEntry(player.getName());
				} else if (plg.getTeam(Teams.RED).hasEntry(player.getName())) {
					plg.getTeam(Teams.RED).removeEntry(player.getName());
				}

				player.getInventory().setHelmet(null);
				player.getInventory().setChestplate(null);
				player.getInventory().setLeggings(null);
				player.getInventory().setBoots(null);

				player.getWorld().setPVP(false);

			}
			plg.setCurrentGameState(GameState.PREPARE);
			plg.getServer().getScheduler().cancelTask(task.getTaskId());
		}

		if (plg.getCurrentGameState() == GameState.GAMING) {
			if (count > 0) {
				Score TimeScore = plg.getObj().getScore(ChatColor.GOLD + "残り時間: ");
				TimeScore.setScore(count);

				Score RedPoint = plg.getObj().getScore(ChatColor.DARK_RED + "赤チーム獲得ポイント: ");
				RedPoint.setScore(plg.getRedPoint());

				Score BluePoint = plg.getObj().getScore(ChatColor.DARK_BLUE + "青チーム獲得ポイント: ");
				BluePoint.setScore(plg.getBluePoint());

				TitleSender title = new TitleSender();
				if (!plg.getMyConfig().isCanRespawn()) {
					for (Player p : plg.getServer().getOnlinePlayers()) {
						title.sendTitle(p, null, null, ChatColor.RED + "赤チーム残り人数: " + plg.getTeam(Teams.RED).getEntries().size()
								+ "  " + ChatColor.BLUE + "青チーム残り人数: " + plg.getTeam(Teams.BLUE).getEntries().size());
					}
				}

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
