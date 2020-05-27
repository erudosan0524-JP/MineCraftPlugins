package com.github.jp.erudo.eanticheat;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.jp.erudo.eanticheat.listener.FoodLevelListener;
import com.github.jp.erudo.eanticheat.listener.JoinLeaveListener;
import com.github.jp.erudo.eanticheat.listener.PlayerInteractListener;
import com.github.jp.erudo.eanticheat.listener.PlayerMoveListener;
import com.github.jp.erudo.eanticheat.utils.User;

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
		new JoinLeaveListener(this);
		new PlayerInteractListener(this);
		new FoodLevelListener(this);

		for(Player p : getServer().getOnlinePlayers()) {
			USERS.put(p.getUniqueId(), new User(p));
		}
	}

	public static User getUser(Player p) {
		return USERS.get(p.getUniqueId());
	}

}
