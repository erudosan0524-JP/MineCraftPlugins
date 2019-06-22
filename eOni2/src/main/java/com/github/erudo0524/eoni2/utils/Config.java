package com.github.erudo0524.eoni2.utils;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.erudo0524.eoni2.Main;

public class Config {

	private final Main plg;
	private FileConfiguration config = null;

	//ここからConfigの内容用の変数
	private int defaultTime;
	private boolean ModeHue;


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
		ModeHue = config.getBoolean("isModeHue");
	}

	public int getDefaultTime() {
		return defaultTime;
	}

	public boolean isModeHue() {
		return ModeHue;
	}



}
