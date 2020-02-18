package com.github.jp.erudo.mathematics.listener;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.util.Vector;

import com.github.jp.erudo.mathematics.Main;

public class BowShotListener implements Listener {

	private Main plg;

	public BowShotListener(Main plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onShoot(EntityShootBowEvent e) {
		if(!(e.getEntity() instanceof Player)) {
			return;
		}

		Player player = (Player) e.getEntity();
		Location loc = player.getLocation();

		double arrowAngle = 45; //45度
		double totalAngle1 = loc.getYaw() + arrowAngle;
		double arrowDirX1 = Math.sin(Math.toRadians(totalAngle1));
		double arrowDirZ1 =  Math.cos(Math.toRadians(totalAngle1));

		double totalAngle2 = loc.getYaw() - arrowAngle;
		double arrowDirX2 = Math.sin(Math.toRadians(totalAngle2));
		double arrowDirZ2 = Math.cos(Math.toRadians(totalAngle2));

		Vector arrowDir1 = new Vector(arrowDirX1,loc.getDirection().getY(),arrowDirZ1);
		Vector arrowDir2 = new Vector(arrowDirX2,loc.getDirection().getY(),arrowDirZ2);

		player.launchProjectile(Arrow.class,arrowDir1);
		player.launchProjectile(Arrow.class,arrowDir2);
	}
}
