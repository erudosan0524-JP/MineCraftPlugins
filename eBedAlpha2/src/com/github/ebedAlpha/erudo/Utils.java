package com.github.ebedAlpha.erudo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class Utils {

	private static List<Player> SleepingPlayers = new ArrayList<Player>();

	public static void addPlayer(Player p) {
		if (SleepingPlayers.contains(p)) {
			return;
		} else {
			SleepingPlayers.add(p);
		}
	}

	public static void removePlayer(Player p) {
		if (SleepingPlayers.contains(p)) {
			SleepingPlayers.remove(p);
		} else {
			return;
		}
	}

	public static List<Player> getPlayers() {
		return SleepingPlayers;
	}

	public static void initPlayers() {
		SleepingPlayers = new ArrayList<Player>();
	}
}
