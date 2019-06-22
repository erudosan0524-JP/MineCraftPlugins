package com.github.erudo0524.eoni2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.scheduler.BukkitTask;

import com.github.erudo0524.eoni2.enums.GameState;
import com.github.erudo0524.eoni2.enums.Teams;
import com.github.erudo0524.eoni2.utils.MessageManager;

import net.md_5.bungee.api.ChatColor;

public class CommandManager implements CommandExecutor {

	private Main plg;
	private Game game;
	private BukkitTask task;

	public CommandManager(Main plg) {
		this.plg = plg;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;

		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("start")) {
				if (args.length > 1) {
					int i = 0;
					try {
						i = Integer.parseInt(args[1]);
					} catch (NumberFormatException e) {
						MessageManager.sendMessage(player, "数値を入力してください");
						return true;
					}

					gameStart(i);

					return true;

				} else {
					int i = plg.getDefaultTime();

					gameStart(i);

					return true;

				}
			} else if (args[0].equalsIgnoreCase("wp")) {
				if (args.length > 1) {
					if (plg.getServer().getPlayer(args[1]) == null) {
						MessageManager.sendMessage(player, "指定したプレイヤーは存在しません");
						return true;
					} else {
						if (plg.getOniPos() == null) {
							MessageManager.sendMessage(plg.getServer().getPlayer(args[1]), "TP場所が設定されていません");
							return true;
						} else {
							plg.setOni(plg.getServer().getPlayer(args[1]), plg.getOniPos());
							MessageManager.messageAll(plg.getServer().getPlayer(args[1]).getName() + "が鬼に選ばれました");
							return true;
						}
					}
				} else {
					List<Player> wpPlayerList = new ArrayList<Player>();

					//岩盤の上に立っているプレイヤーをリストに入れる
					for (Player p : Bukkit.getServer().getOnlinePlayers()) {
						if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.BEDROCK) {
							wpPlayerList.add(p);
						}
					}

					//ランダムにリストからプレイヤーを選出
					Random r = new Random();
					Player wpPlayer = null;

					try {
						wpPlayer = wpPlayerList.get(r.nextInt(wpPlayerList.size()));
						if (plg.getOniPos() == null) {
							MessageManager.sendMessage(wpPlayer, "TP場所が設定されていません");
						} else {
							plg.setOni(wpPlayer, plg.getOniPos());
							MessageManager.messageAll(wpPlayer.getName() + "が鬼に選ばれました");
						}
					}catch(IllegalArgumentException e) {
						MessageManager.sendMessage(player, "岩盤の上にいるプレイヤーが見つかりませんでした");
					}

					return true;
				}
			} else if (args[0].equalsIgnoreCase("settppos")) {
				MessageManager.sendMessage(player, "テレポート座標を現在位置に指定しました");
				plg.setTpPos(player.getLocation());
				return true;

			} else if (args[0].equalsIgnoreCase("setonipos")) {
				MessageManager.sendMessage(player, "鬼のTPポイントを現在位置に指定しました");
				plg.setOniPos(player.getLocation());
				return true;

			} else if(args[0].equalsIgnoreCase("setblockpos")) {
				Block block = player.getEyeLocation().getBlock();
				plg.setRemoveBlockPos(block.getLocation());
				MessageManager.sendMessage(player, "削除するブロックの座標を " + ChatColor.WHITE + block.getX() + "," + block.getY() + "," +  block.getZ() + ChatColor.AQUA + "に設定しました");
				return true;
			}else if(args[0].equalsIgnoreCase("version")) {
				PluginDescriptionFile pdf = plg.getDescription();
				MessageManager.sendMessage(player, "Version: " + pdf.getVersion());
			}
		}
		player.sendMessage("使い方:");
		return false;

	}

	@SuppressWarnings("deprecation")
	private void gameStart(int time) {
		//ゲーム状態をGAMINGに変更
		plg.setCurrentGameState(GameState.GAMING);

		game = new Game(plg, time);
		task = plg.getServer().getScheduler().runTaskTimer(plg, game, 0L, 20L);

		MessageManager.broadcastMessage("鬼ごっこスタート!");
		game.setTask(task);

		//鬼以外のプレイヤーをPlayerチームに振り分け
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (!plg.getTeam(Teams.ONI).hasEntry(p.getName())) {
				plg.addPlayerToTeam(Teams.PLAYER, p);
			}

			//全員シフト状態に
			p.setSneaking(true);
		}
	}

}
