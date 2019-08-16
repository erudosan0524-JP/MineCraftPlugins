package com.github.erudo.ebowspleef.utils;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.erudo.ebowspleef.Main;

public class Config {
	private final Main plg;
	private FileConfiguration config = null;

	//ここからConfigの内容用の変数
	private int arrowRange;
	private int defaultTime;

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

		arrowRange = config.getInt("ArrowRange");
		defaultTime = config.getInt("DefaultTime");
	}

	public int getArrowRange() {
		return arrowRange;
	}

	public int getDefaultTime() {
		return defaultTime;
	}

}
