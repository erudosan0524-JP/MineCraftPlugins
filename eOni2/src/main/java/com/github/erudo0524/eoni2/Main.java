package com.github.erudo0524.eoni2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.github.erudo0524.eoni2.enums.GameState;
import com.github.erudo0524.eoni2.enums.Teams;
import com.github.erudo0524.eoni2.events.DamagePlayerListener;

public class Main extends JavaPlugin {

	private ScoreboardManager manager;
	private Scoreboard board;
	private Objective obj;
	private Team oni;
	private Team player;

	//ゲームの状態
	private GameState currentGameState;

	private Location tpPos;
	private Location oniPos;

	public final String objName = "情報";

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");

		manager = Bukkit.getScoreboardManager();
		board = manager.getMainScoreboard();
		obj = board.getObjective(objName);

		if(obj != null) {
//			this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "scoreboard objectives remove " + objName);
			obj.unregister();
		}

		if(board.getTeam(Teams.ONI.getName()) != null) {
			board.getTeam(Teams.ONI.getName()).unregister();
		}else if (board.getTeam(Teams.PLAYER.getName()) != null) {
			board.getTeam(Teams.PLAYER.getName()).unregister();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		getLogger().info("プラグインが読み込まれました");

		////////////////////////
		///		Command		///
		///////////////////////
		getCommand("eoni").setExecutor(new CommandManager(this));

		///////////////////////////
		///		ScoreBoard		///
		///////////////////////////
		manager = Bukkit.getScoreboardManager();
		board = manager.getMainScoreboard();
		obj = board.getObjective(objName);
		//無かったら作る
		if(board.getObjective(objName) == null) {
			obj = board.registerNewObjective(objName, "dummy");
		}
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		//Team
		if(board.getTeam(Teams.ONI.getName()) == null) {
			oni = board.registerNewTeam(Teams.ONI.getName());
		} else {
			oni = board.getTeam(Teams.ONI.getName());
		}
		oni.setPrefix("§c");
		oni.setSuffix(ChatColor.WHITE.toString());
		oni.setDisplayName("鬼");
		oni.setAllowFriendlyFire(false);

		if(board.getTeam(Teams.PLAYER.getName()) == null) {
			player = board.registerNewTeam(Teams.PLAYER.getName());
		} else {
			player = board.getTeam(Teams.PLAYER.getName());
		}
		player.setPrefix("§9");
		player.setSuffix(ChatColor.WHITE.toString());
		player.setDisplayName("人");
		player.setAllowFriendlyFire(false);

		//とりあえず全員Playerに
		for(String s : oni.getEntries()) {
			player.addEntry(s);
		}


		///////////////////////
		///		Listener 	///
		///////////////////////
		new DamagePlayerListener(this);

		setCurrentGameState(GameState.PREPARE);
	}

	public Team getTeam(Teams teams) {
		if (teams == Teams.ONI) {
			return oni;
		} else {
			return player;
		}
	}

	public void removePlayerFromTeam(Teams teams, Player p) {
		if (teams == Teams.ONI) {
			oni.removeEntry(player.getName());
		} else {
			player.removeEntry(p.getName());
		}
	}

	public void addPlayerToTeam(Teams teams, Player p) {
		if (teams == Teams.ONI) {
			oni.addEntry(p.getName());
		} else if (teams == Teams.PLAYER) {
			player.addEntry(p.getName());
		}
	}

	public Objective getObj() {
		return obj;
	}

	public void setObj(Objective obj) {
		this.obj = obj;
	}

	public Scoreboard getBoard() {
		return board;
	}

	public void setBoard(Scoreboard board) {
		this.board = board;
	}

	public GameState getCurrentGameState() {
		return currentGameState;
	}

	public void setCurrentGameState(GameState state) {
		this.currentGameState = state;
	}

	public Location getTpPos() {
		return tpPos;
	}

	public void setTpPos(Location tpPos) {
		this.tpPos = tpPos;
	}

	public Location getOniPos() {
		return oniPos;
	}

	public void setOniPos(Location oniPos) {
		this.oniPos = oniPos;
	}

}
