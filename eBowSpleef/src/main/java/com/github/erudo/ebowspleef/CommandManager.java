package com.github.erudo.ebowspleef;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.erudo.ebowspleef.utils.MessageManager;

public class CommandManager implements CommandExecutor {

	private Main plg;


	public CommandManager(Main main) {
		this.plg = main;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;

		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("start")) {

				if (!player.hasPermission("ebs.start")) {
					return true;
				}

				if (args.length > 1) {
					int i = 0;
					try {
						i = Integer.parseInt(args[1]);
					} catch (NumberFormatException e) {
						MessageManager.sendMessage(player, "数値を入力してください");
						return true;
					}

					plg.GameStart(i);

					return true;

				} else {
					int i = plg.getDefaultTime();

					plg.GameStart(i);

					return true;

				}
			}
		}
		return false;
	}
}
