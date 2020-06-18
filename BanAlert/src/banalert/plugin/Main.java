package banalert.plugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが読み込まれました");
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onKick(PlayerKickEvent e) {
		Player p = e.getPlayer();
		if (p.isBanned() || Bukkit.getServer().getBannedPlayers().contains(p)) {
			Bukkit.getServer().broadcastMessage(p.getName() + "がBANされました");
		} else {
			Bukkit.getServer().broadcastMessage(p.getName() + "がkickされました");
		}

	}
}