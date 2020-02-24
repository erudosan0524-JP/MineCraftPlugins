package jp.github.erudo.nowater.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.plugin.java.JavaPlugin;

import jp.github.erudo.nowater.MathUtils;
import jp.github.erudo.nowater.NoWater;

public class OnBlockDispense implements Listener {

	JavaPlugin plg;

	public OnBlockDispense(JavaPlugin plugin) {
		this.plg = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onBlockDispense(BlockDispenseEvent e) {
		Material item = e.getItem().getType();
		Location loc = e.getBlock().getLocation();

		double x = MathUtils.eFloor(loc.getX(), 1);
		double y = MathUtils.eFloor(loc.getY(), 1);
		double z = MathUtils.eFloor(loc.getZ(), 1);

		if (item == Material.WATER_BUCKET) {
			e.setCancelled(true);

			Bukkit.broadcastMessage(ChatColor.AQUA + "[NoWater]水の発生が" + ChatColor.WHITE + loc.getWorld().getName()
					+ ChatColor.AQUA + "の" + ChatColor.WHITE + " x: " + x +
					" y: " + y + " z: " + z + ChatColor.AQUA
					+ "で検知されました");

			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (p.isOp() || p.hasPermission("nowater.admin")) {
					NoWater.sendHoverText(p, ChatColor.RED + "発生場所(" + x + " , " + y + " , " + z + ")にテレポートする",
							"クリックしてテレポート", "/minecraft:tp " + p.getName() + " " + x + " " + y + " " + z);
				}
			}

		}
	}
}
