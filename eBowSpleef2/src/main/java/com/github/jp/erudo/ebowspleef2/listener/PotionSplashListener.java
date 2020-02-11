package com.github.jp.erudo.ebowspleef2.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.enums.GameState;
import com.github.jp.erudo.ebowspleef2.runnable.SplashPotionRunnable;

import net.md_5.bungee.api.ChatColor;

public class PotionSplashListener implements Listener {

	private Main plg;
	private BukkitTask task;

	public PotionSplashListener(Main plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onSplashPotion(PotionSplashEvent e) {

		if(!(plg.getCurrentGameState() == GameState.GAMING)) {
			return;
		}

		for(PotionEffect effects :e.getPotion().getEffects()) {
			//エフェクト判定
			if(effects.getType() == PotionEffectType.BLINDNESS
					&& effects.getType() == PotionEffectType.SLOW
					&& effects.getType() == PotionEffectType.CONFUSION) {

				//名前判定
				if(ChatColor.stripColor(e.getPotion().getItem().getItemMeta().getDisplayName()).equals("魔法のポーション")) {
					int count = plg.getMyConfig().getPotionInterval();

					SplashPotionRunnable runnable = new SplashPotionRunnable(plg,count,(Player)e.getEntity());
					runnable.setTask(task);
				}
			}
		}
	}

}
