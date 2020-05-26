package com.github.jp.erudo.eanticheat;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.jp.erudo.eanticheat.checks.CheckResult;
import com.github.jp.erudo.eanticheat.listener.PlayerMoveListener;
import com.github.jp.erudo.eanticheat.utils.MessageManager;
import com.github.jp.erudo.eanticheat.utils.Settings;
import com.github.jp.erudo.eanticheat.utils.User;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {

	public static HashMap<UUID,User> USERS = new HashMap<UUID,User>();

	public static final String pluginName = "eAntiCheat";

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました。");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました。");

		///////////////////////////
		///		Listener		///
		///////////////////////////
		new PlayerMoveListener(this);

		for(Player p : getServer().getOnlinePlayers()) {
			USERS.put(p.getUniqueId(), new User(p));
		}
	}

	public static void log(CheckResult cr, User u) {
		String message = MessageManager.prefix + cr.getType() + "; " + cr.getMessage() + ChatColor.RED + "【" + cr.getLevel() + "】";
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.hasPermission(Settings.ALERT)) {
				p.sendMessage(message);
			}
		}
	}

}
