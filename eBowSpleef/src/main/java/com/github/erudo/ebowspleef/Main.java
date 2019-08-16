package com.github.erudo.ebowspleef;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.github.erudo.ebowspleef.enums.GameState;
import com.github.erudo.ebowspleef.enums.Teams;
import com.github.erudo.ebowspleef.listener.ArrowListener;
import com.github.erudo.ebowspleef.listener.JoinLeaveListener;
import com.github.erudo.ebowspleef.runnable.CountDown;
import com.github.erudo.ebowspleef.runnable.Game;
import com.github.erudo.ebowspleef.utils.Config;
import com.github.erudo.ebowspleef.utils.MessageManager;

public class Main extends JavaPlugin {

	private ScoreboardManager manager;
	private Scoreboard board;
	private Objective obj;
	private Team spectator;
	private Team redTeam;
	private Team blueTeam;
	public final String objName = "情報";

	private Config config;

	private GameState gameState;

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが読み込まれました");

		////////////////////////////
		///		Commands		///
		///////////////////////////
		getCommand("ebs").setExecutor(new CommandManager(this));

		//Config
		config = new Config(this);

		////////////////////////////
		///		Events			///
		///////////////////////////
		new JoinLeaveListener(this);
		new ArrowListener(this);

		///////////////////////////
		///		ScoreBoard		///
		///////////////////////////
		manager = Bukkit.getScoreboardManager();
		board = manager.getMainScoreboard();
		obj = board.getObjective(objName);
		//無かったら作る
		if (board.getObjective(objName) == null) {
			obj = board.registerNewObjective(objName, "dummy");
		}
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		//Team

		if (board.getTeam(Teams.RED.getName()) == null) {
			redTeam = board.registerNewTeam(Teams.RED.getName());
		} else {
			redTeam = board.getTeam(Teams.RED.getName());
		}
		redTeam.setPrefix("§c");
		redTeam.setSuffix(ChatColor.WHITE.toString());
		redTeam.setDisplayName("赤チーム");
		redTeam.setAllowFriendlyFire(false);

		if (board.getTeam(Teams.BLUE.getName()) == null) {
			blueTeam = board.registerNewTeam(Teams.BLUE.getName());
		} else {
			blueTeam = board.getTeam(Teams.BLUE.getName());
		}
		blueTeam.setPrefix("§9");
		blueTeam.setSuffix(ChatColor.WHITE.toString());
		blueTeam.setDisplayName("青チーム");
		blueTeam.setAllowFriendlyFire(false);

		if (board.getTeam(Teams.SPECTATOR.getName()) == null) {
			spectator = board.registerNewTeam(Teams.SPECTATOR.getName());
		} else {
			spectator = board.getTeam(Teams.SPECTATOR.getName());
		}
		spectator.setPrefix("§7");
		spectator.setSuffix(ChatColor.WHITE.toString());
		spectator.setDisplayName("観客");
		spectator.setAllowFriendlyFire(false);

		//GameState設定
		setGameState(GameState.PREPARE);
	}

	@SuppressWarnings("deprecation")
	public void GameStart(int time) {
		Game game;
		BukkitTask task;
		this.setGameState(GameState.GAMING);

		game = new Game(this, time);
		task = this.getServer().getScheduler().runTaskTimer(this, game, 0L, 20L);

		MessageManager.broadcastMessage("ゲームスタート!");
		game.setTask(task);
	}

	@SuppressWarnings("deprecation")
	public void CountStart(int count, int time) {
		CountDown countDown;
		BukkitTask task;

		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (!(this.getTeam(Teams.BLUE).hasEntry(p.getName()) || this.getTeam(Teams.RED).hasEntry(p.getName()))) {
				this.addPlayerToTeam(Teams.SPECTATOR, p);
				p.setGameMode(GameMode.SPECTATOR);
			}

			p.setGameMode(GameMode.SURVIVAL);
			p.setSneaking(true);
		}

		countDown = new CountDown(this, count, time);
		task = this.getServer().getScheduler().runTaskTimer(this, countDown, 0L, 20L);
		countDown.setTask(task);

	}

	public Objective getObj() {
		return obj;
	}

	public Team getTeam(Teams teams) {
		if (teams == Teams.SPECTATOR) {
			return spectator;
		} else if (teams == Teams.RED) {
			return redTeam;
		} else {
			return blueTeam;
		}
	}

	public void removePlayerFromTeam(Teams teams, Player p) {
		if (teams == Teams.SPECTATOR) {
			spectator.removeEntry(p.getName());
		} else if (teams == Teams.RED) {
			redTeam.removeEntry(p.getName());
		} else {
			blueTeam.removeEntry(p.getName());
		}
	}

	public void addPlayerToTeam(Teams teams, Player p) {
		if (teams == Teams.SPECTATOR) {
			spectator.addEntry(p.getName());
		} else if (teams == Teams.RED) {
			redTeam.addEntry(p.getName());
		} else {
			blueTeam.addEntry(p.getName());
		}
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public int getArrowRange() {
		return config.getArrowRange();
	}

	public int getDefaultTime() {
		return config.getDefaultTime();
	}

	public int getDefaultCount() {
		return config.getDefaultCount();
	}
}
