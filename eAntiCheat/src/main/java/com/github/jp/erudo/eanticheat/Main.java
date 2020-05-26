package com.github.jp.erudo.eanticheat;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました。");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました。");
	}


}
