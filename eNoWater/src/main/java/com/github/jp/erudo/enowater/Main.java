package com.github.jp.erudo.enowater;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.jp.erudo.enowater.listener.WaterDispenceListener;
import com.github.jp.erudo.enowater.listener.WaterPlaceListener;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class Main extends JavaPlugin {

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました");
		new WaterDispenceListener(this);
		new WaterPlaceListener(this);
	}

	public static void sendHoverText(Player p, String text, String hoverText, String command) {
		HoverEvent hoverEvent = null;
		if(hoverText != null) {
			BaseComponent[] hover =new ComponentBuilder(hoverText).create();
			hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT,hover);
		}

		ClickEvent clickEvent = null;
		if(command != null) {
			clickEvent = new ClickEvent(Action.RUN_COMMAND, command);
		}

		BaseComponent[] message = new ComponentBuilder(text).event(hoverEvent).event(clickEvent).create();
		p.spigot().sendMessage(message);
	}
}
