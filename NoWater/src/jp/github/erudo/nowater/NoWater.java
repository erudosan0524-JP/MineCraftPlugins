package jp.github.erudo.nowater;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import jp.github.erudo.nowater.events.OnBlockDispense;
import jp.github.erudo.nowater.events.OnPlace;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class NoWater extends JavaPlugin{

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが読み込まれました");
		new OnPlace(this);
		new OnBlockDispense(this);
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
