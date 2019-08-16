package com.github.erudo.ebowspleef.listener;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.erudo.ebowspleef.Main;
import com.github.erudo.ebowspleef.enums.GameState;
import com.gmail.stevenpcc.arrowhitblockevent.ArrowHitBlockEvent;

public class ArrowListener implements Listener {

	Main plg;

	public ArrowListener(Main main) {
		this.plg = main;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	//https://dev.bukkit.org/projects/arrowhitblockevent
	@EventHandler
	public void onArrowHitBlock(ArrowHitBlockEvent e) {
		if (!plg.getGameState().equals(GameState.GAMING)) {
			return;
		}

		//ここから着地点処理
		Block block = e.getBlock();
		Location currentBlockLoc = block.getLocation();
		Location blockLoc1 = new Location(block.getWorld(), currentBlockLoc.getX() + 1, currentBlockLoc.getY(),
				currentBlockLoc.getZ());
		Location blockLoc2 = new Location(block.getWorld(), currentBlockLoc.getX(), currentBlockLoc.getY(),
				currentBlockLoc.getZ() + 1);
		Location blockLoc3 = new Location(block.getWorld(), currentBlockLoc.getX() + 1, currentBlockLoc.getY(),
				currentBlockLoc.getZ() + 1);
		if (plg.getArrowRange() <= 1) {
			block.setType(Material.AIR);
		} else {
			currentBlockLoc.getBlock().setType(Material.AIR);
			blockLoc1.getBlock().setType(Material.AIR);
			blockLoc2.getBlock().setType(Material.AIR);
			blockLoc3.getBlock().setType(Material.AIR);
		}
		currentBlockLoc.getWorld().createExplosion(currentBlockLoc.getX(), currentBlockLoc.getY(),
				currentBlockLoc.getZ(), 10, false, false);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onLaunch(ProjectileLaunchEvent e) {
		if (!plg.getGameState().equals(GameState.GAMING)) {
			return;
		}


		//ここから軌道の処理
		Projectile p = e.getEntity();

		AddEffect addEffect = new AddEffect(p);

		this.plg.getServer().getScheduler().scheduleSyncRepeatingTask(plg, addEffect, 0, 10);
	}

	private class AddEffect extends BukkitRunnable {
		Projectile p;
		Location l;
		boolean flag = false;

		public AddEffect(Projectile p) {
			this.p = p;
			l = p.getLocation();
		}

		public void run() {
			if (flag)
				return;
			if (p.getLocation().distance(l) == 0) {
				//前の実行から動いていない=何かに当たった
				cancel();//自身の実行をキャンセル
				flag = true;//何故かキャンセルしても動いてしまうのでcancelを1回のみ実行するように
			}
			p.getWorld().playEffect(p.getLocation(), Effect.SMOKE, 0);
		}
	}

}
