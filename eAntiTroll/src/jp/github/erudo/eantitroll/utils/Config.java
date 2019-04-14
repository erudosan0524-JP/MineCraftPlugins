package jp.github.erudo.eantitroll.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {

	private final Plugin plg;
	private FileConfiguration config = null;

	private int WarningCount;

	public Config(Plugin plg) {
		this.plg = plg;

		load();
	}

	public static Config getInstance(Plugin plg) {
		return new Config(plg);
	}

	public void load() {
		plg.saveDefaultConfig();
		if(config != null) {
			plg.reloadConfig();
		}

		config = plg.getConfig();

		WarningCount = config.getInt("Warning-Count");

	}

	public int getWarningCount() {
		return WarningCount;
	}
}
