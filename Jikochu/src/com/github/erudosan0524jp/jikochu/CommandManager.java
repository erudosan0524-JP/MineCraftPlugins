package com.github.erudosan0524jp.jikochu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import com.github.erudosan0524jp.jikochu.runnable.Counter;
import com.github.erudosan0524jp.jikochu.utils.Datas;
import com.github.erudosan0524jp.jikochu.utils.EMath;
import com.github.erudosan0524jp.jikochu.utils.Inventory;

public class CommandManager implements CommandExecutor {

	JavaPlugin plg;
	Player player;
	BukkitTask task;
	int num;

	public CommandManager(JavaPlugin plg) {
		this.plg = plg;
	}

	@SuppressWarnings({ "null", "deprecation" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}

		player = (Player) sender;


		if(args.length != 0) {
			if(args[0].equalsIgnoreCase("chusin")) {
				if(args[1].length() != 0) {
					Player p = plg.getServer().getPlayer(args[1]);

					if (p == null) {
						player.sendMessage(p.getName() + "というプレイヤーは存在しません！");

						return true;
					}

					p.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,3600 * 20, 3600 * 20), false);
					Inventory.setHelmet(p, Material.BANNER);
					Datas.setE_Player(p);

					for(Player p1 : plg.getServer().getOnlinePlayers()) {
						p1.sendMessage(ChatColor.AQUA + p.getName() + "が中心人物に設定されました");
					}

					return true;
				}
				return false;

			}else if(args[0].equalsIgnoreCase("range")) {
				if(args[1].length() != 0) {
					try {
						num = Integer.parseInt(args[1]);
					}catch(NumberFormatException e) {
						player.sendMessage("数字を指定してください");
						return true;
					}catch(NullPointerException e) {
						player.sendMessage(ChatColor.DARK_RED + "NULL");
						return true;
					}

					Datas.setRange(num);
					for(Player p1 : plg.getServer().getOnlinePlayers()) {
						p1.sendMessage(ChatColor.AQUA + "範囲が" + num + "に設定されました");
					}
					return true;
				}
				return false;

			}else if(args[0].equalsIgnoreCase("start")) {
				Counter counter = new Counter(plg, 3);
				task = plg.getServer().getScheduler().runTaskTimer(plg, counter, 0L, 20L);
				counter.setTask(task);

				for(Player p : plg.getServer().getOnlinePlayers()) {




				}
				return true;

			}else if(args[0].equalsIgnoreCase("circle")) {
				try {
					EMath.drawCircle(Datas.E_Player.getLocation(), Datas.range);
					return true;
				}catch(NullPointerException e) {
					player.sendMessage("半径を設定してください");
					return true;
				}

			}
		}
		player.sendMessage(ChatColor.GREEN + "========= " + ChatColor.GOLD + "自己中プラグイン" + ChatColor.GREEN + " =========");
		player.sendMessage("・/jikochu chusin [プレイヤー名]");
		player.sendMessage("・/jikochu range [数字]");

		return false;
	}

}
