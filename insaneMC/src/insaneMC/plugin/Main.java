package insaneMC.plugin;

import java.awt.Color;

import org.bukkit.plugin.java.JavaPlugin;

import insaneMC.plugin.event.EventManager;
import insaneMC.plugin.utils.Mobs;

public class Main extends JavaPlugin {

	@Override
	public void onDisable() {
		getLogger().info(Color.red + "プラグインが停止しました");
		Mobs.initList();
	}

	@Override
	public void onEnable() {
		getLogger().info(Color.red + "プラグインが読み込まれました");
		new EventManager(this);
	}


}
