package com.github.erudosan0524.jp.ewhitelist;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class EWhitelist extends JavaPlugin {

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました");
		getCommand("ewhitelist").setExecutor(new CommandManager(this));
		CustomConfig config = new CustomConfig(this);
		CustomConfig config2 = new CustomConfig(this,"IDlist.yml");
		config.saveDefaultConfig();
		config2.saveDefaultConfig();

		if(config.getConfig().getBoolean("enabled-load")) {
			getServer().dispatchCommand(getServer().getConsoleSender(), "ewhitelist addall");
		}
	}

	public static void sendMessage(Player p, String s) {
		p.sendMessage(ChatColor.AQUA + "[eWhitelist]" + ChatColor.WHITE + s);
	}


}
