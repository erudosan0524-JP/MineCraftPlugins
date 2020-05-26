package com.github.jp.erudo.eanticheat;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.jp.erudo.eanticheat.listener.PlayerMoveListener;

public class Main extends JavaPlugin {

	public static final String pluginName = "eAntiCheat";

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました。");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました。");

		///////////////////////////
		///		Listener		///
		///////////////////////////
		new PlayerMoveListener(this);
	}

}
