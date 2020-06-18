package com.github.erudo0524.eoni2;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.github.erudo0524.eoni2.enums.GameState;
import com.github.erudo0524.eoni2.enums.Teams;
import com.github.erudo0524.eoni2.events.DamagePlayerListener;
import com.github.erudo0524.eoni2.events.PlayerMoveListener;
import com.github.erudo0524.eoni2.utils.Config;

public class Main extends JavaPlugin {

	//TODO:Config.ymlからポジション取得したいができていない

	private ScoreboardManager manager;
	private Scoreboard board;
	private Objective obj;
	private Team oni;
	private Team player;

	//ゲームの状態
	private GameState currentGameState;

	//コンフィグ
	public static Config config;

	private Location oniPos;
	private Location tpPos;
	private Location deleteBlockPos;
	private List<Location> supplyChestPos;
	private String defaultWorld;

	public final String objName = "情報";

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");

		manager = Bukkit.getScoreboardManager();
		board = manager.getMainScoreboard();
		obj = board.getObjective(objName);

		if (obj != null) {
			//			this.getServer().dispatchCommand(this.getServer().getConsoleSender(), "scoreboard objectives remove " + objName);
			obj.unregister();
		}

		if (board.getTeam(Teams.ONI.getName()) != null) {
			board.getTeam(Teams.ONI.getName()).unregister();
		} else if (board.getTeam(Teams.PLAYER.getName()) != null) {
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

		///////////////////////
		///		Config		///
		///////////////////////
		config = new Config(this);

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
		if (board.getTeam(Teams.ONI.getName()) == null) {
			oni = board.registerNewTeam(Teams.ONI.getName());
		} else {
			oni = board.getTeam(Teams.ONI.getName());
		}
		oni.setPrefix("§c");
		oni.setSuffix(ChatColor.WHITE.toString());
		oni.setDisplayName("鬼");
		oni.setAllowFriendlyFire(false);

		if (board.getTeam(Teams.PLAYER.getName()) == null) {
			player = board.registerNewTeam(Teams.PLAYER.getName());
		} else {
			player = board.getTeam(Teams.PLAYER.getName());
		}
		player.setPrefix("§9");
		player.setSuffix(ChatColor.WHITE.toString());
		player.setDisplayName("人");
		player.setAllowFriendlyFire(false);

		//とりあえず全員Playerに
		for (String s : oni.getEntries()) {
			oni.removeEntry(s);
			player.addEntry(s);
		}

		///////////////////////
		///		Listener 	///
		///////////////////////
		new DamagePlayerListener(this);
		new PlayerMoveListener(this);

		setCurrentGameState(GameState.PREPARE);
		supplyChestPos = new ArrayList<Location>();

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

	public Location getDeleteBlockPos() {
		return deleteBlockPos;
	}

	public void setDeleteBlockPos(Location deleteBlockPos) {
		this.deleteBlockPos = deleteBlockPos;
	}

	public List<Location> getSupplyChestPos() {
		return supplyChestPos;
	}

	public void addSupplyChestPos(Location supplyChestPos) {
		this.supplyChestPos.add(supplyChestPos);
	}

	public void setSupplyChestPos(List<Location> locs) {
		this.supplyChestPos = locs;
	}

	public void setOni(Player player, Location tpLoc) {
		for (Team team : player.getScoreboard().getTeams()) {
			if (team.getName() == Teams.PLAYER.getName()) {
				this.removePlayerFromTeam(Teams.PLAYER, player);
			}
		}

		this.addPlayerToTeam(Teams.ONI, player);
		player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
		player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
		player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));

		player.teleport(tpLoc);
	}

	//ここからConfig関連
	public int getDefaultTime() {
		return config.getDefaultTime();
	}

	public boolean isModeHue() {
		return config.isModeHue();
	}

	public int getSupplyChestInterval() {
		return config.getSupplyChestInterval();
	}

	public World getDefaultWorld() {
		return this.getServer().getWorld(config.getDefaultWorld());
	}

	public Location getOniPos(World world) {
		return new Location(world, config.getConfOniPos()[0], config.getConfOniPos()[1], config.getConfOniPos()[2]);
	}

	public Location getTpPos(World world) {
		return new Location(world, config.getConfOniPos()[0], config.getConfOniPos()[1], config.getConfOniPos()[2]);
	}

	public Location getDeleteBlockPos(World world) {
		return deleteBlockPos = new Location(world, config.getConfDeleteBlockPos()[0],
				config.getConfDeleteBlockPos()[1], config.getConfDeleteBlockPos()[2]);
	}

	public List<Location> getSupplyChestPos(World world) {
		List<Location> array = new ArrayList<Location>();
		for (int[] d : config.getConfSupplyChestPos()) {
			array.add(new Location(world, d[0], d[1], d[2]));
		}
		return array;
	}
}
