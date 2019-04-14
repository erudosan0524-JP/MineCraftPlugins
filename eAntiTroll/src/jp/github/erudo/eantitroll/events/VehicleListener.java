package jp.github.erudo.eantitroll.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.plugin.java.JavaPlugin;

import jp.github.erudo.eantitroll.utils.MathUtils;
import jp.github.erudo.eantitroll.utils.MessageManager;

public class VehicleListener implements Listener {

	JavaPlugin plg;

	public VehicleListener(JavaPlugin plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onVehicleCreate(VehicleCreateEvent event) {
		Entity vehicle = event.getVehicle();
		Location loc = event.getVehicle().getLocation();

		double x = MathUtils.eFloor(loc.getX(), 1);
		double y = MathUtils.eFloor(loc.getY(), 1);
		double z = MathUtils.eFloor(loc.getZ(), 1);

		if (vehicle.getType() == EntityType.MINECART_TNT) {
			Bukkit.broadcastMessage(ChatColor.AQUA + "[eAntiTroll]TNTトロッコの発生が" + ChatColor.WHITE + loc.getWorld()
					+ ChatColor.AQUA + "の" + ChatColor.WHITE + " x: " + x +
					" y: " + y + " z: " + z + ChatColor.AQUA + "で検知されました");

			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp() || p.hasPermission("eantitroll.admin")) {
//					if (EAntiTroll.MVFlag) {
//						EAntiTroll.sendHoverText(p, ChatColor.RED + "発生場所(" +loc.getWorld().getName() + " , "
//								+ x + " , " + y + " , " + z + ")にテレポートする"
//								, "クリックしてテレポート", "/mv tp " + p.getName() + " " + loc.getWorld().getName());
//					} else {
						MessageManager.sendHoverText(p, ChatColor.RED + "発生場所(" + x + " , " + y + " , " + z + ")にテレポートする",
								"クリックしてテレポート", "/minecraft:tp " + p.getName() + " " + x + " " + y + " " + z);
//					}
				}
			}

			event.setCancelled(true);
		}
	}
}
