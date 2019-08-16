package com.github.erudo.ebowspleef;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.github.erudo.ebowspleef.runnable.Game;
import com.github.erudo.ebowspleef.utils.MessageManager;

public class CommandManager implements CommandExecutor {

	private Main plg;
	private Game game;
	private BukkitTask task;

	public CommandManager(Main main) {
		this.plg = main;
	}

	@SuppressWarnings("deprecation")
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

					game = new Game(plg, i);
					task = plg.getServer().getScheduler().runTaskTimer(plg, game, 0L, 20L);

					MessageManager.broadcastMessage("ゲームスタート!");
					game.setTask(task);

					return true;

				} else {
					int i = plg.getDefaultTime();

					game = new Game(plg, i);
					task = plg.getServer().getScheduler().runTaskTimer(plg, game, 0L, 20L);

					MessageManager.broadcastMessage("ゲームスタート!");
					game.setTask(task);

					return true;

				}
			}
		}
		return false;
	}
}
