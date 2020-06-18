package com.github.erudosan0524jp.jikochu;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.erudosan0524jp.jikochu.events.EventListener;
import com.github.erudosan0524jp.jikochu.runnable.GameRunnable;
import com.github.erudosan0524jp.jikochu.utils.Config;

public class Jikochu extends JavaPlugin{


	public static boolean isGameStarting = false;

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		getLogger().info("プラグインが有効になりました");
		BukkitRunnable game = new GameRunnable();
		getServer().getScheduler().scheduleAsyncRepeatingTask(this, game, 0L, 20L);


		getServer().getPluginCommand("jikochu").setExecutor(new CommandManager(this));

		new EventListener(this);
		new Config(this);
	}
}
