package com.github.jp.erudo.eantitroll.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.jp.erudo.eantitroll.Main;
import com.github.jp.erudo.eantitroll.utils.MathUtils;
import com.github.jp.erudo.eantitroll.utils.MessageManager;

import net.md_5.bungee.api.ChatColor;

public class DispenserListener implements Listener {

	public DispenserListener(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onBlockDispense(BlockDispenseEvent e) {
		Material item = e.getItem().getType();
		Location loc = e.getBlock().getLocation();

		double x = MathUtils.eFloor(loc.getX(), 1);
		double y = MathUtils.eFloor(loc.getY(), 1);
		double z = MathUtils.eFloor(loc.getZ(), 1);

		if (item == Material.LAVA_BUCKET) {
			e.setCancelled(true);

			Bukkit.broadcastMessage(ChatColor.AQUA + "[eAntiTroll]マグマの発生が" + ChatColor.WHITE + loc.getWorld().getName()
					+ ChatColor.AQUA + "の" + ChatColor.WHITE + " x: " + x +
					" y: " + y + " z: " + z + ChatColor.AQUA
					+ "で検知されました");
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (p.isOp() || p.hasPermission("eantitroll.admin")) {

					if (Main.MVFlag) {
						if (loc.getWorld() == p.getLocation().getWorld()) {
							MessageManager.sendHoverText(p,
									ChatColor.RED + "発生場所(" + x + " , " + y + " , " + z + ")にテレポートする",
									"クリックしてテレポート", "/tp " + p.getName() + " " + x + " " + y + " " + z);
						} else {
							MessageManager.sendHoverText(p, ChatColor.RED + "発生場所(" + loc.getWorld().getName() + " , "
									+ x + " , " + y + " , " + z + ")にテレポートする", "クリックしてテレポート",
									"/mv tp " + p.getName() + " " + loc.getWorld().getName());
						}
					} else {

						MessageManager.sendHoverText(p,
								ChatColor.RED + "発生場所(" + x + " , " + y + " , " + z + ")にテレポートする",
								"クリックしてテレポート", "/tp " + p.getName() + " " + x + " " + y + " " + z);

					}
				}
			}
		} else if (item == Material.TNT) {
			e.setCancelled(true);

			Bukkit.broadcastMessage(ChatColor.AQUA + "[eAntiTroll]TNTの発生が" + ChatColor.WHITE + loc.getWorld().getName()
					+ ChatColor.AQUA + "の" + ChatColor.WHITE + " x: " + x +
					" y: " + y + " z: " + z + ChatColor.AQUA
					+ "で検知されました");

			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (p.isOp() || p.hasPermission("eantitroll.admin")) {
					if (Main.MVFlag) {
						if (loc.getWorld() == p.getLocation().getWorld()) {
							MessageManager.sendHoverText(p,
									ChatColor.RED + "発生場所(" + x + " , " + y + " , " + z + ")にテレポートする",
									"クリックしてテレポート", "/tp " + p.getName() + " " + x + " " + y + " " + z);
						} else {
							MessageManager.sendHoverText(p, ChatColor.RED + "発生場所(" + loc.getWorld().getName() + " , "
									+ x + " , " + y + " , " + z + ")にテレポートする", "クリックしてテレポート",
									"/mv tp " + p.getName() + " " + loc.getWorld().getName());
						}
					} else {
						MessageManager.sendHoverText(p,
								ChatColor.RED + "発生場所(" + x + " , " + y + " , " + z + ")にテレポートする",
								"クリックしてテレポート", "/tp " + p.getName() + " " + x + " " + y + " " + z);
					}

				}
			}
		}
	}

}
