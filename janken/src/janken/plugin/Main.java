package janken.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました。");
		getServer().getScheduler().cancelAllTasks();
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが読み込まれました。");
		getServer().getPluginCommand("janken").setExecutor(new CommandManager(this));
	}
}
