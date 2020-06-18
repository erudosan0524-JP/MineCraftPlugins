package com.github.erudosan0524jp.jikochu.runnable;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.erudosan0524jp.jikochu.Jikochu;
import com.github.erudosan0524jp.jikochu.utils.Datas;

import net.md_5.bungee.api.ChatColor;

public class GameRunnable extends BukkitRunnable {

	public static HashMap<Player,Integer> Warningcount = new HashMap<>();


	@Override
	public void run() {
		if(Jikochu.isGameStarting) {
//			double x = Datas.getE_Player().getLocation().getX();
//			double y = Datas.getE_Player().getLocation().getY();
//			double z = Datas.getE_Player().getLocation().getZ();

			for(Player p : Bukkit.getServer().getOnlinePlayers()) {
				if(Datas.getE_Player().getLocation().distance(p.getLocation()) > Datas.getRange()) {
					for(Player p2 : Bukkit.getServer().getOnlinePlayers()) {
						p2.sendMessage(ChatColor.RED + p.getName() + "さんが" + Datas.E_Player.getName() + "から離れようとしています！！");
						p2.damage(4);

						if(Warningcount.containsKey(p)) {
							Warningcount.put(p, Warningcount.get(p)+1);
						} else {
							Warningcount.put(p, 1);
						}
					}
				}
			}

		}


	}
}
