package jp.github.erudo.eantitroll.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import jp.github.erudo.eantitroll.utils.User;

public class JoinQuitListener implements Listener {

	JavaPlugin plg;

	public JoinQuitListener(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
		this.plg = plg;
	}

	@EventHandler
	public void onJoinEvent(PlayerJoinEvent e) {
		Player p = e.getPlayer();

		if(User.map.containsKey(p.getName())) {
			User.addMap(p.getName(), p);
		}else {
			User.removeMap(p.getName());
		}
	}


	@EventHandler
	public void onQuitEvent(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		if(User.map.containsKey(p.getName())) {
			User.removeMap(p.getName());
		}
	}

}
