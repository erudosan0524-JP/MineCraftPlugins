package janken.plugin;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import janken.plugin.runnable.GameStart;
import janken.plugin.runnable.Repeater;
import janken.plugin.utils.User;

public class CommandManager implements CommandExecutor {

	private JavaPlugin plg;
	private BukkitTask task;

	public CommandManager(JavaPlugin plg) {
		this.plg = plg;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;
		User user = new User();

		if (args.length != 0) {
			if (args[0].equalsIgnoreCase("tohyo")) {
				if (args[1].equalsIgnoreCase("guu")) {
					if (user.tehuda.containsKey(player)) {
						player.sendMessage("手札を変えることはできません");
						return true;
					}
					user.setTehuda(player, 1);
					player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 1);
					return true;

				} else if (args[1].equalsIgnoreCase("tyoki")) {
					if (user.tehuda.containsKey(player)) {
						player.sendMessage("手札を変えることはできません");
						return true;
					}
					user.setTehuda(player, 2);
					player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 1);
					return true;

				} else if (args[1].equalsIgnoreCase("paa")) {
					if (user.tehuda.containsKey(player)) {
						player.sendMessage("手札を変えることはできません");
						return true;
					}
					user.setTehuda(player, 3);
					player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.8F, 1);
					return true;
				}
			} else if (args[0].equalsIgnoreCase("gamestart")) {
				GameStart game = new GameStart(plg);
				task = plg.getServer().getScheduler().runTaskTimer(plg, game, 0L, 20L);
				game.settask(task);
				return true;
			}else if(args[0].equalsIgnoreCase("start")) {
				Repeater repeat = new Repeater(player);
				plg.getServer().getScheduler().scheduleAsyncRepeatingTask(plg, repeat, 0L, 20L);
				return true;
			}
		}
		player.sendMessage(ChatColor.YELLOW + "＝＝＝＝コマンド＝＝＝＝");
		player.sendMessage("・/janken tohyo guu/tyoki/paa");

		return false;
	}

}
