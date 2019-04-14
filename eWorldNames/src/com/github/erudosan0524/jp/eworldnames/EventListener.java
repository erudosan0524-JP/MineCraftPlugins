package com.github.erudosan0524.jp.eworldnames;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.erudosan0524.jp.eworldnames.utils.Config;
import com.github.erudosan0524.jp.eworldnames.utils.TitleSender;

public class EventListener implements Listener {

	TitleSender title;
	JavaPlugin plg;

	public EventListener(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
		this.plg = plg;
	}

	@EventHandler
	public void onChangeWorld(PlayerChangedWorldEvent e) {
		Player player = e.getPlayer();

		title = new TitleSender();
		Config config = new Config(plg,player.getWorld());

		if(plg.getConfig().isSet(player.getWorld().getName())) { //TPしたワールドがconfigに登録されていたら
			player.playSound(player.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, config.getSoundVolume(), 1);
			title.sendTitle(player, config.getTitle(player.getWorld()), config.getSubTitle(player.getWorld()), null);
		}
	}
}
