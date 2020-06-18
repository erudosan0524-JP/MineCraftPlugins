package com.github.erudosan0524.jp.etablist;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Config {

	private static final Charset CONFIG_CHARSET = StandardCharsets.UTF_8;

	JavaPlugin plg;

	private String color;
	private String serverName;

	public Config(JavaPlugin plg, World world) {
		this.plg = plg;

		load(world);
	}

	private void load(World world) {
		plg.saveDefaultConfig();

		String configFilePath = plg.getDataFolder() + File.separator + "config.yml";

		try (Reader reader = new InputStreamReader(new FileInputStream(configFilePath), CONFIG_CHARSET)) {

			FileConfiguration conf = new YamlConfiguration();

			conf.load(reader);


			color = conf.getString(world.getName() + ".color");
			serverName = conf.getString("serverName");

		} catch (Exception e) {
			System.out.println(e);

			plg.onDisable();
		}
	}

	public String getColor() {
		return ChatColor.translateAlternateColorCodes('&', color);
	}

	public String getServerName() {
		return serverName;
	}
}
