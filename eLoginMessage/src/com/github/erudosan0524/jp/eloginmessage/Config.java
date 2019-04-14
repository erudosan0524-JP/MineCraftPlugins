package com.github.erudosan0524.jp.eloginmessage;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {

	//Charsetの設定
	private static final Charset CONFIG_CHARSET = StandardCharsets.UTF_8;

	JavaPlugin plg;
	FileConfiguration conf;

	List<String> Loginmessages;

	public Config(JavaPlugin plg) {
		this.plg = plg;

		load();
	}

	private void load() {
		plg.saveDefaultConfig();

		String configFilePath = plg.getDataFolder() + File.separator + "config.yml";

		try (Reader reader = new InputStreamReader(new FileInputStream(configFilePath), CONFIG_CHARSET)) {

			// 設定データ入出力クラスを作る
			FileConfiguration conf = new YamlConfiguration();

			// 設定を読み込む
			conf.load(reader);

			Loginmessages = conf.getStringList("LoginMessages");

		} catch (Exception e) {
			System.out.println(e);

			plg.onDisable();
		}
	}

	public List<String> getLoginMessages() {
		List<String> messages = new ArrayList<>();

		for(String s : Loginmessages) {
			messages.add(ChatColor.translateAlternateColorCodes('&', s));
		}

		return messages;
	}
}
