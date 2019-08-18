package com.github.erudo.ebowspleef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.erudo.ebowspleef.enums.Teams;
import com.github.erudo.ebowspleef.utils.MessageManager;

import net.md_5.bungee.api.ChatColor;

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

					plg.CountStart(plg.getDefaultCount(), i);

					return true;

				} else {
					int i = plg.getDefaultTime();

					plg.CountStart(plg.getDefaultCount(), i);

					return true;

				}
			} else if (args[0].equalsIgnoreCase("join")) {
				if (args[1].equalsIgnoreCase("red")) {
					plg.addPlayerToTeam(Teams.RED, player);
					MessageManager.sendMessage(player, "あなたは" + ChatColor.RED + "赤チーム " + ChatColor.WHITE + "に参加した");
					return true;
				} else if (args[1].equalsIgnoreCase("blue")) {
					plg.addPlayerToTeam(Teams.BLUE, player);
					MessageManager.sendMessage(player, "あなたは" + ChatColor.BLUE + "青チーム " + ChatColor.WHITE + "に参加した");
					return true;
				} else {
					HashMap<Teams, ChatColor> teams = new HashMap<Teams, ChatColor>();
					teams.put(Teams.RED, ChatColor.RED);
					teams.put(Teams.BLUE, ChatColor.BLUE);
					List<Teams> keysAsArray = new ArrayList<Teams>(teams.keySet());
					Random r = new Random();
					Teams key = keysAsArray.get(r.nextInt(keysAsArray.size()));
					plg.addPlayerToTeam(key, player);
					MessageManager.sendMessage(player, "あなたは" + teams.get(key) + key.getName() + ChatColor.WHITE + "に参加した");
					return true;
				}
			} else if(args[0].equalsIgnoreCase("setredpos")) {
				plg.setRedPosition(player.getLocation());
				MessageManager.sendMessage(player, "赤チームの拠点を設定しました。");
			} else if(args[0].equalsIgnoreCase("setbluepos")) {
				plg.setBluePosition(player.getLocation());
				MessageManager.sendMessage(player, "青チームの拠点を設定しました。");
			}
		}
		return false;
	}
}
