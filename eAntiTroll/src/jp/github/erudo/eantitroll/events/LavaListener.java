package jp.github.erudo.eantitroll.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.plugin.java.JavaPlugin;

import jp.github.erudo.eantitroll.utils.Config;
import jp.github.erudo.eantitroll.utils.MessageManager;
import jp.github.erudo.eantitroll.utils.User;

public class LavaListener implements Listener {

	JavaPlugin plg;
	Material bucket = null;
	Player player = null;
	Block target = null;
	Location location = null;

	User user = new User(plg);

	public LavaListener(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
		this.plg = plg;
	}

	@EventHandler
	public void placeLava(PlayerBucketEmptyEvent event) {
		bucket = event.getBucket();
		player = event.getPlayer();
		target = player.getTargetBlock(null, 100);

		String PlayerName = player.getName();

		if (bucket == Material.LAVA_BUCKET) {

			if (player.isOp() || player.hasPermission("eantitroll.lava") || player.hasPermission("eantitroll.lava." + player.getWorld().getName()) || player.hasPermission("eantitroll.admin")) {
				return;
			}

			if (target.isLiquid()) {
				if (target.getType() == Material.LAVA || target.getType() == Material.STATIONARY_LAVA) {
					return;
				}
			}
			event.setCancelled(true);

			Bukkit.broadcastMessage("§4" + PlayerName + "がマグマを置こうとしています！！");

			User.addWarning(player.getUniqueId());

			if (User.getWarning(player.getUniqueId()) == Config.getInstance(plg).getWarningCount()) {
				MessageManager.sendBANMessage(player);
				User.initWarnig(player.getUniqueId());

				Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage(ChatColor.GOLD + player.getName() + "が荒らしの疑いでBANされました"));
			}

			User.setFreezing(player.getUniqueId(),true);

		}
	}

}
