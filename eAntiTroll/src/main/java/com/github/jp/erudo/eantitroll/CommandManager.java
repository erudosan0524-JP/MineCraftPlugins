package com.github.jp.erudo.eantitroll;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.jp.erudo.eantitroll.utils.User;


public class CommandManager implements CommandExecutor {

	JavaPlugin plg;

	public CommandManager(JavaPlugin plg) {
		this.plg = plg;
	}

	@SuppressWarnings("null")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;

		if (args.length != 0) {
			if (args[0].equalsIgnoreCase("kaijo")) {
				if(!player.hasPermission("eantitroll.kaijo")) {
					return true;
				}

				if (args[1].length() != 0) {
					Player p = plg.getServer().getPlayer(args[1]);

					if (p == null) {
						player.sendMessage(p.getName() + "というプレイヤーは存在しません！");

						return true;
					}

					if(User.isFreezing(p.getUniqueId())) {
						User.setFreezing(p.getUniqueId(),false);
					}else {
						p.sendMessage(p.getName() + "は禁止状態になっていません");
						return true;
					}

					Bukkit.getOnlinePlayers().forEach(p_ -> p_.sendMessage(ChatColor.AQUA + "[eAntiTroll]" + p.getName() + "の移動禁止を解除しました"));
					return true;

				}

				return false;
			}else if(args[0].equalsIgnoreCase("kidou")) {
				if(args[1].length() != 0) {
					if(!player.hasPermission("eantitroll.kidou")) {
						return true;
					}

					Player p = plg.getServer().getPlayer(args[1]);

					if(p == null) {
						player.sendMessage(p.getName() + "というプレイヤーは存在しません！");
						return true;
					}

					if(!User.isFreezing(p.getUniqueId())) {
						User.setFreezing(p.getUniqueId(),true);
					}else {
						p.sendMessage(p.getName() + "はすでに禁止状態です");
						return true;
					}

					Bukkit.getOnlinePlayers().forEach(p_ -> p_.sendMessage(ChatColor.AQUA + "[eAntiTroll]" + p.getName() + "の移動禁止にしました"));

					return true;
				}
			}
			return false;
		}
		player.sendMessage("【使い方】");
		player.sendMessage("・ /eAntiTroll kaijo [プレイヤー名]");
		player.sendMessage("・ /eAntiTroll kidou [プレイヤー名]");
		return true;
	}

}
