package com.github.erudo.advancedec;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

	private final Main plg;
	private FileConfiguration config = null;

	//ここからConfigの内容用の変数
	private int chestrow;

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

		chestrow = config.getInt("row");
	}

	public int getChestRow() {
		return chestrow;
	}
}
