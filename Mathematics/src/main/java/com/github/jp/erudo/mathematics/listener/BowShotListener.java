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


	public BowShotListener(Main plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onShoot(EntityShootBowEvent e) {
		if(!(e.getEntity() instanceof Player)) {
			return;
		}

		double multiply = e.getProjectile().getVelocity().length();

		Player player = (Player) e.getEntity();
		Location loc = player.getLocation();

		double arrowAngle = 15;

		double totalAngle1 = (((loc.getYaw()+90) + arrowAngle) * Math.PI)/180;
		double arrowDirX1 = Math.cos(totalAngle1);
		double arrowDirZ1 =  Math.sin(totalAngle1);

		double totalAngle2 = (((loc.getYaw()+90) - arrowAngle) * Math.PI)/180;
		double arrowDirX2 = Math.cos(totalAngle2);
		double arrowDirZ2 = Math.sin(totalAngle2);

		Vector arrowDir1 = new Vector(arrowDirX1,loc.getDirection().getY(),arrowDirZ1).normalize().multiply(multiply);
		Vector arrowDir2 = new Vector(arrowDirX2,loc.getDirection().getY(),arrowDirZ2).normalize().multiply(multiply);

		player.launchProjectile(Arrow.class,arrowDir1);
		player.launchProjectile(Arrow.class,arrowDir2);
	}
}
