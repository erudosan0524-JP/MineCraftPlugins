package com.github.ebed.erudo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if(args.length != 0) {
			if(args[0].equalsIgnoreCase("check")) {
				for(int i=0; i > Utils.getPlayers().size(); i++) {
					player.sendMessage(Utils.getPlayers().get(i).toString());
				}
				return true;
			}
		}
		player.sendMessage("使い方： /ebed check");
		return false;
	}

}
