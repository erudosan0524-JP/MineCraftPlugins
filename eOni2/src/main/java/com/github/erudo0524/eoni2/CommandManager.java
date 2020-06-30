package com.github.erudo0524.eoni2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.DoubleChest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.scheduler.BukkitTask;

import com.github.erudo0524.eoni2.enums.GameState;
import com.github.erudo0524.eoni2.enums.Teams;
import com.github.erudo0524.eoni2.utils.MessageManager;

public class CommandManager implements CommandExecutor {

	private Main plg;
	private Game game;

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

				if (!player.hasPermission("eoni.start")) {
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

					gameStart(i, player);

					return true;

				} else {
					int i = plg.getDefaultTime();

					try {
						gameStart(i, player);
					} catch (NullPointerException e) {
						MessageManager.sendMessage(player, "Nullが検知されました。");
					}

					return true;

				}
			} else if (args[0].equalsIgnoreCase("wp")) {
				if (!player.hasPermission("eoni.wp")) {
					return true;
				}

				if (args.length > 1) {
					if (plg.getServer().getPlayer(args[1]) == null) {
						MessageManager.sendMessage(player, "指定したプレイヤーは存在しません");
						return true;
					} else {
						if (plg.getOniPos() == null) {
							plg.setOniPos(plg.getOniPos(player.getWorld()));
							plg.setOni(plg.getServer().getPlayer(args[1]), plg.getOniPos());
							MessageManager.messageAll(plg.getServer().getPlayer(args[1]).getName() + "が鬼に選ばれました");
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
							plg.setOniPos(plg.getOniPos(player.getWorld()));
							plg.setOni(wpPlayer, plg.getOniPos());
							MessageManager.messageAll(wpPlayer.getName() + "が鬼に選ばれました");
						} else {
							plg.setOni(wpPlayer, plg.getOniPos());
							MessageManager.messageAll(wpPlayer.getName() + "が鬼に選ばれました");
						}
					} catch (IllegalArgumentException e) {
						MessageManager.sendMessage(player, "岩盤の上にいるプレイヤーが見つかりませんでした");
					}

					return true;
				}
			} else if (args[0].equalsIgnoreCase("settppos")) {
				if (!player.hasPermission("eoni.set.tp")) {
					return true;
				}

				MessageManager.sendMessage(player, "テレポート座標を現在位置に指定しました");
				plg.setTpPos(player.getLocation());
				return true;

			} else if (args[0].equalsIgnoreCase("setonipos")) {
				if (!player.hasPermission("eoni.set.oni")) {
					return true;
				}

				MessageManager.sendMessage(player, "鬼のTPポイントを現在位置に指定しました");
				plg.setOniPos(player.getLocation());
				return true;

			} else if (args[0].equalsIgnoreCase("setblockpos")) {
				if (!player.hasPermission("eoni.set.block")) {
					return true;
				}

				Block block = player.getTargetBlock(null, 20);
				plg.setDeleteBlockPos(block.getLocation());
				MessageManager.sendMessage(player,
						"削除するブロックの座標を " + ChatColor.WHITE + block.getX() + "," + block.getY() + "," + block.getZ() +
								ChatColor.AQUA + "の" + ChatColor.WHITE + block.getType() + ChatColor.AQUA + "に設定しました");
				return true;
			} else if (args[0].equalsIgnoreCase("setchestpos")) {
				if (!player.hasPermission("eoni.set.chest")) {
					return true;
				}

				Block block = player.getTargetBlock(null, 20);

				if (block.getType() == Material.CHEST) {

					if (block.getState() instanceof DoubleChest) {
						MessageManager.sendMessage(player, "ラージチェストはサプライチェストに登録できません");
						return true;
					}

					plg.addSupplyChestPos(block.getLocation());
					MessageManager.sendMessage(player,
							block.getX() + "," + block.getY() + "," + block.getZ() + "をサプライチェストに登録しました");

					return true;
				} else {
					MessageManager.sendMessage(player, "チェストではないためサプライチェストに登録できません");
					return true;
				}
			} else if (args[0].equalsIgnoreCase("version")) {
				PluginDescriptionFile pdf = plg.getDescription();
				MessageManager.sendMessage(player, "Version: " + pdf.getVersion());
				return true;
			}
		}
		MessageManager.CommandContent(player);
		return false;

	}

	private void gameStart(int time, Player player) {
		//ゲーム状態をGAMINGに変更
		plg.setCurrentGameState(GameState.GAMING);

		if (!player.getWorld().getPVP()) {
			player.getWorld().setPVP(true);
		}

		if (plg.getTpPos() == null || plg.getSupplyChestPos() == null) {
			plg.setTpPos(plg.getTpPos(player.getWorld()));
			plg.setSupplyChestPos(plg.getSupplyChestPos(player.getWorld()));
		}

		game = new Game(plg, time);
		@SuppressWarnings("unused")
		BukkitTask task = game.runTaskTimer(plg, 0, 20L);

		MessageManager.broadcastMessage("鬼ごっこスタート!");

		//鬼以外のプレイヤーをPlayerチームに振り分け
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (!plg.getTeam(Teams.ONI).hasEntry(p.getName())) {
				plg.addPlayerToTeam(Teams.PLAYER, p);
			}

			//全員シフト状態に
			p.setSneaking(true);
		}

		//指定したブロックを削除
		if (plg.getDeleteBlockPos() == null) {
			plg.setDeleteBlockPos(plg.getDeleteBlockPos(player.getWorld()));
		}
		plg.getDeleteBlockPos().getBlock().setType(Material.AIR);
	}

}
