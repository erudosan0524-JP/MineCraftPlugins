package com.github.erudo.ebowspleef.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.erudo.ebowspleef.Main;

public class JoinLeaveListener implements Listener{

	Main plg;

	public JoinLeaveListener(Main main) {
		this.plg = main;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {

	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {

	}

}
