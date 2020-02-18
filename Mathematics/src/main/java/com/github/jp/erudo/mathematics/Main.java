package com.github.jp.erudo.mathematics;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.jp.erudo.mathematics.listener.BowShotListener;

public class Main extends JavaPlugin {

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました");

		new BowShotListener(this);
	}

}
