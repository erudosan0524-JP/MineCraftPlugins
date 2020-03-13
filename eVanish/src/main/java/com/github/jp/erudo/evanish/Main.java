package com.github.jp.erudo.evanish;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.jp.erudo.evanish.listener.PlayerJoinListener;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {

	private HashMap<Player, Boolean> isVanished = new HashMap<Player, Boolean>();

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました");
		new PlayerJoinListener(this);

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;

		if (player.isOp() || player.hasPermission("evanish.admin")) {
			if (isVanished.get(player)) { //true
				for (Player online : Bukkit.getServer().getOnlinePlayers()) {
					online.showPlayer(this, player);
				}

				isVanished.put(player, false);

				sendMessage(player);

			} else {
				for (Player online : Bukkit.getServer().getOnlinePlayers()) {
					online.hidePlayer(this, player);
				}

				isVanished.put(player, true);

				sendMessage(player);
			}

			return true;
		}

		return false;
	}

	private void sendMessage(Player sender) {
		String pluginName = ChatColor.AQUA + "【eVanish】" + ChatColor.WHITE;

		if (isVanished.get(sender)) { //true
			sender.sendMessage(pluginName + "透明化が有効になりました");
		} else {
			sender.sendMessage(pluginName + "透明化が解除されました");
		}
	}

	public HashMap<Player, Boolean> getIsVanished() {
		return isVanished;
	}

}
