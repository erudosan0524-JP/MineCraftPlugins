package com.github.erudosan0524.jp.ewhitelist;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomConfig {

	private FileConfiguration config = null;
	private final File configFile;
	private final String file;
	private final JavaPlugin plg;

	public CustomConfig(JavaPlugin plg) {
		this(plg,"config.yml");
	}

	public CustomConfig(JavaPlugin plg, String fileName) {
		this.plg = plg;
		this.file = fileName;
		configFile = new File(plg.getDataFolder(),file);
	}

	public void saveDefaultConfig() {
		if (!configFile.exists()) {
			plg.saveResource(file, false);
		}
	}

	public FileConfiguration getConfig() {
		if (config == null) {
			reloadConfig();
		}
		return config;
	}

	public void saveConfig() {
		if (config == null) {
			return;
		}
		try {
			getConfig().save(configFile);
		} catch (IOException ex) {
			plg.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
		}
	}

	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(configFile);

		final InputStream defConfigStream = plg.getResource(file);
		if (defConfigStream == null) {
			return;
		}

		config.setDefaults(
				YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, StandardCharsets.UTF_8)));
	}
}
