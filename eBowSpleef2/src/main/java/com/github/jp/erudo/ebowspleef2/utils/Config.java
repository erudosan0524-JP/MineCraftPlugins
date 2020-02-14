package com.github.jp.erudo.ebowspleef2.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import com.github.jp.erudo.ebowspleef2.Main;

public class Config {

	private final Main plg;
	private FileConfiguration config = null;

	//ここからConfigの内容用の変数
	private int defaultTime;
	private String confRedPos;
	private String confBluePos;
	private String confLobbyPos;
	private int arrowrange;
	private boolean canRespawn;
	private String beginCoordinate;
	private String stageName;

	public Config(Main plg) {
		this.plg = plg;
		load();
	}

	private void load() {
		plg.saveDefaultConfig();
		if (config != null) { // configが非null == リロードで呼び出された
			plg.reloadConfig();
		}
		config = plg.getConfig();

		defaultTime = config.getInt("defaultTime");
		confRedPos = config.getString("RedPosition");
		confBluePos = config.getString("BluePosition");
		confLobbyPos = config.getString("LobbyPosition");
		arrowrange = config.getInt("ArrowRange");
		canRespawn = config.getBoolean("canRespawn");
		beginCoordinate = config.getString("beginCoordinate");
		stageName = config.getString("stageName","stage1"); //デフォルトはstage1
	}

	//座標を取得
	private int[] getCoordinates(String[] str) {
		for (String arg : str) {
			if (arg.isEmpty() || str.equals(null)) {
				plg.getServer().getLogger().info("strはnullでした");
				return null;
			}
		}

		//[x,y,z]->[0,1,2]
		int[] num = new int[3];
		for (int i = 0; i < 3; i++) {
			num[i] = Integer.parseInt(str[i]);
		}
		return num;
	}

	public int[] getRedPosition() {
		String[] str = this.confRedPos.split(" ",0);

		return getCoordinates(str);
	}

	public Location getRedPosition(World world) {
		return new Location(world, this.getRedPosition()[0], this.getRedPosition()[1], this.getRedPosition()[2]);
	}

	public int[] getBluePosition() {
		String[] str = this.confBluePos.split(" ",0);

		return getCoordinates(str);
	}

	public Location getBluePosition(World world) {
		return new Location(world, this.getBluePosition()[0], this.getBluePosition()[1], this.getBluePosition()[2]);
	}

	public int[] getLobbyPosition() {
		String[] str = this.confLobbyPos.split(" ",0);

		return getCoordinates(str);
	}

	public Location getLobbyPosition(World world) {
		return new Location(world, this.getLobbyPosition()[0], this.getLobbyPosition()[1], this.getLobbyPosition()[2]);
	}

	public int getDefaultTime() {
		return this.defaultTime;
	}

	public void setConfRedPos(String confRedPos) {
	}

	public void setConfBluePos(String confBluePos) {
		this.confBluePos = confBluePos;
	}

	public void setConfLobbyPos(String confLobbyPos) {
		this.confLobbyPos = confLobbyPos;
	}

	public int getArrowrange() {
		return arrowrange;
	}

	public void setArrowrange(int arrowrange) {
		this.arrowrange = arrowrange;
	}

	public boolean isCanRespawn() {
		return canRespawn;
	}

	public void setCanRespawn(boolean canRespawn) {
		this.canRespawn = canRespawn;
	}

	public int[] getBeginCoordinate() {
		String[] str = this.beginCoordinate.split(" ");

		return getCoordinates(str);
	}

	public void setBeginCoordinate(String beginCoordinate) {
		this.beginCoordinate = beginCoordinate;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}



}
