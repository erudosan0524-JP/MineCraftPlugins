package com.github.erudosan0524.jp.eworldnames;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class EWorldNames extends JavaPlugin implements Listener {



	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました");

		new EventListener(this);
	}



}
