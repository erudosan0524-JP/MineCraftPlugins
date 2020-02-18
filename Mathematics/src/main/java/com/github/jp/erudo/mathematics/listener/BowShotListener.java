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
		double totalAngle = loc.getYaw() + arrowAngle;
		double arrowDirX = Math.sin(Math.toRadians(totalAngle));
		double arrowDirZ =  Math.cos(Math.toRadians(totalAngle));

		Vector arrowDir = new Vector(arrowDirX,loc.getDirection().getY(),arrowDirZ);

		player.launchProjectile(Arrow.class,arrowDir);
	}
}
