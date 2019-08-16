package com.github.erudo.ebowspleef;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.github.erudo.ebowspleef.enums.GameState;
import com.github.erudo.ebowspleef.listener.ArrowListener;
import com.github.erudo.ebowspleef.listener.DamagePlayerListener;
import com.github.erudo.ebowspleef.listener.JoinLeaveListener;
import com.github.erudo.ebowspleef.utils.Config;

public class Main extends JavaPlugin {

	private ScoreboardManager manager;
	private Scoreboard board;
	private Objective obj;
	public final String objName = "情報";

	private Config config;

	private GameState gameState;

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@SuppressWarnings("deprecation")
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
		new DamagePlayerListener(this);

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

		//GameState設定
		setGameState(GameState.PREPARE);
	}

	public Objective getObj() {
		return obj;
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

}
