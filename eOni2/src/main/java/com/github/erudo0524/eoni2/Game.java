package com.github.erudo0524.eoni2;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Score;

import com.github.erudo0524.eoni2.enums.GameState;
import com.github.erudo0524.eoni2.enums.Teams;

public class Game extends BukkitRunnable {

	private final Main plg;
	private BukkitTask task;
	private int count;


	public Game(Main _plg, int _count) {
		this.plg = _plg;
		this.count = _count;
	}

	public void run() {
		if(plg.getCurrentGameState() == GameState.END) {
			count = 0;
			MessageManager.broadcastMessage("鬼ごっこ終了!");
			plg.getServer().getScheduler().cancelTask(task.getTaskId());
		}

		if(plg.getCurrentGameState() == GameState.GAMING) {
			if (count > 0) {
				Score TimeScore = plg.getObj().getScore(ChatColor.GOLD + "残り時間: ");
				TimeScore.setScore(count);

				Score PlayersNumScore = plg.getObj().getScore(ChatColor.DARK_BLUE + "プレイヤー人数: ");
				PlayersNumScore.setScore(plg.getTeam(Teams.PLAYER).getEntries().size());

				Score OnisNumScore = plg.getObj().getScore(ChatColor.DARK_RED + "鬼人数: ");
				OnisNumScore.setScore( + plg.getTeam(Teams.ONI).getEntries().size());

				//人数判定
				if(plg.getTeam(Teams.PLAYER).getEntries().size() <= 0) {
					plg.setCurrentGameState(GameState.END);
				}

				if(plg.getTeam(Teams.ONI).getEntries().size() <= 0) {
					plg.setCurrentGameState(GameState.END);
					MessageManager.messageAll("鬼が０人だったため試合が終了しました");
				}

				if(plg.getTpPos() == null || plg.getOniPos() == null) {
					plg.setCurrentGameState(GameState.END);
					MessageManager.messageAll("TP場所が正しく設定されていなかったため試合が終了しました");
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
