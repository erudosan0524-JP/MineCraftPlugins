package janken.plugin.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class User {

	//0デフォルト
	//1グー
	//2チョキ
	//3パー

	public HashMap<Player, Integer> tehuda = new HashMap<>();

	public HashMap<Player, Integer> getTehuda() {
		return tehuda;
	}

	public int getTehuda(Player p) {
		return this.tehuda.get(p);
	}

	public void setTehuda(Player p, int i) {
		this.tehuda.put(p, i);
	}

	public void removeTehuda() {
		this.tehuda.clear();
	}
}
