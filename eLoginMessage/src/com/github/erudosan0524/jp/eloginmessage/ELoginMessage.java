package com.github.erudosan0524.jp.eloginmessage;

import org.bukkit.plugin.java.JavaPlugin;

public class ELoginMessage extends JavaPlugin {

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました");
		new EventListener(this);

		new Config(this);
	}
}
