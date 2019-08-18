package com.github.erudo.ebowspleef.utils;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.erudo.ebowspleef.Main;

public class Config {
	private final Main plg;
	private FileConfiguration config = null;

	//ここからConfigの内容用の変数
	private int arrowRange;
	private int defaultTime;
	private int defaultCount;
	//x:0 y:1 z:2
	private int[] RedPosition = new int[3];
	private int[] BluePosition = new int[3];

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

		arrowRange = config.getInt("ArrowRange",1);
		defaultTime = config.getInt("DefaultTime",180);
		defaultCount = config.getInt("DefaultCount",3);
		RedPosition[0] = config.getInt("RedPosition.x",0);
		RedPosition[1] = config.getInt("RedPosition.y",0);
		RedPosition[2] = config.getInt("RedPosition.z",0);
		BluePosition[0] = config.getInt("BluePosition.x",0);
		BluePosition[1] = config.getInt("BluePosition.y",0);
		BluePosition[2] = config.getInt("BluePosition.z",0);
	}

	public int getArrowRange() {
		return arrowRange;
	}

	public int getDefaultTime() {
		return defaultTime;
	}

	public int getDefaultCount() {
		return defaultCount;
	}

	public int[] getRedPosition() {
		return RedPosition;
	}

	public void setRedPosition(double x, double y, double z) {
		config.set("RedPosition.x", x);
		config.set("RedPosition.y", y);
		config.set("RedPosition.z", z);
		plg.saveConfig();
	}

	public int[] getBluePosition() {
		return BluePosition;
	}

	public void setBluePosition(double x, double y, double z) {
		config.set("BluePosition.x", x);
		config.set("BluePosition.y", y);
		config.set("BluePosition.z", z);
		plg.saveConfig();
	}
}
