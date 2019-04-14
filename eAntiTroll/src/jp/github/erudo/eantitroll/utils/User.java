package jp.github.erudo.eantitroll.utils;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class User {

	JavaPlugin plg;


	public User(JavaPlugin plg) {
		this.plg = plg;
	}

	public static HashMap<String, Player> map = new HashMap<>();
	public static HashMap<UUID, Integer> warning = new HashMap<>();
	public static HashMap<UUID,Boolean> freezing = new HashMap<>();

	public static void addMap(String player_name, Player p) {
		if (!map.containsKey(player_name)) {
			map.put(player_name, p);
		}
	}

	public static void removeMap(String player_name) {
		if (map.containsKey(player_name)) {
			map.remove(player_name);
		}
	}

	public static int getWarning(UUID uuid) {
		return warning.get(uuid);

	}

	public static void addWarning(UUID uuid) {
		if (warning.containsKey(uuid)) {
			getWarning(uuid);
			warning.put(uuid, warning.get(uuid) + 1);
		} else {
			warning.put(uuid, 0);
		}
	}

	public static void initWarnig(UUID uuid) {
		if (warning.containsKey(uuid)) {
			warning.remove(uuid);
		}
	}



	public static boolean isFreezing(UUID uuid) {
		if(freezing.containsKey(uuid)) {
			return freezing.get(uuid);
		} else {
			return false;
		}
	}

	public static void setFreezing(UUID uuid, boolean b) {
		freezing.put(uuid, b);
	}
}
