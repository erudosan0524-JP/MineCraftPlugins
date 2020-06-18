package com.github.ebed.erudo;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.ebed.erudo.events.OnGotoBed;
import com.github.ebed.erudo.events.OnJoin;
import com.github.ebed.erudo.events.OnLeaveBed;

public class Main extends JavaPlugin {

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが読み込まれました");
		new OnGotoBed(this);
		new OnLeaveBed(this);
		new OnJoin(this);

		getServer().getPluginCommand("ebed").setExecutor(new CommandManager());
	}

}
