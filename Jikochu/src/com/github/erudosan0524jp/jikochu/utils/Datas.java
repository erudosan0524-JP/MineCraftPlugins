package com.github.erudosan0524jp.jikochu.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class Datas {

	public static Player E_Player;
	public static int range;

	public static HashMap<Player, Integer> flag = new HashMap<>();

	public static int getFlag(Player p) {
		return flag.get(p);
	}

	public static void setFlag(Player p, int value) {
		if(flag.containsKey(p)) {
			flag.remove(p);
			flag.put(p, value);
		}else {
			flag.put(p, value);
		}
	}

	public static Player getE_Player() {
		return E_Player;
	}

	public static void setE_Player(Player E_Player) {
		Datas.E_Player = E_Player;

	}

	public static int getRange() {
		return range;
	}

	public static void setRange(int range) {
		Datas.range = range;
	}

}
