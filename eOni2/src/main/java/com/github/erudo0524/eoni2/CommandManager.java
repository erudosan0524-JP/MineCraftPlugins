package com.github.erudo0524.eoni2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import com.github.erudo0524.eoni2.enums.GameState;
import com.github.erudo0524.eoni2.enums.Teams;

public class CommandManager implements CommandExecutor {

	private Main plg;

	public CommandManager(Main plg) {
		this.plg = plg;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;
		Game game;
		BukkitTask task;

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

					//ゲーム状態をGAMINGに変更
					plg.setCurrentGameState(GameState.GAMING);

					game = new Game(plg, i);
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

					return true;

				} else {
					int i = 120;

					plg.setCurrentGameState(GameState.GAMING);

					game = new Game(plg, i);
					task = plg.getServer().getScheduler().runTaskTimer(plg, game, 0L, 20L);

					MessageManager.broadcastMessage("鬼ごっこスタート!");
					game.setTask(task);

					for (Player p : Bukkit.getServer().getOnlinePlayers()) {
						if (!plg.getTeam(Teams.ONI).hasEntry(p.getName())) {
							plg.addPlayerToTeam(Teams.PLAYER, p);
						}
						p.setSneaking(true);
					}

					return true;

				}
			} else if (args[0].equalsIgnoreCase("wp")) {
				if (args.length > 1) {
					if (plg.getServer().getPlayer(args[1]) == null) {
						MessageManager.sendMessage(player, "指定したプレイヤーは存在しません");
						return true;
					} else {
						setOni(plg.getServer().getPlayer(args[1]));
						return true;
					}
				} else {
					List<Player> wpPlayerList = new ArrayList<Player>();

					//岩盤の上に立っているプレイヤーをリストに入れる
					for (Player p : Bukkit.getServer().getOnlinePlayers()) {
						if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.BEDROCK) {
							wpPlayerList.add(p);
						}
					}

					//ランダムにリストからプレイヤーを抽出
					Random r = new Random();
					Player wpPlayer = null;

					try {
						wpPlayer = wpPlayerList.get(r.nextInt(wpPlayerList.size()));
						setOni(wpPlayer);
					}catch(IllegalArgumentException e) {
						MessageManager.sendMessage(player, "岩盤の上にいるプレイヤーが見つかりませんでした");
						return true;
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
			}
		}
		player.sendMessage("使い方:");
		return false;

	}

	private void setOni(Player p) {
		if (plg.getOniPos() == null) {
			MessageManager.sendMessage(p, "TP場所が設定されていません");
			return;
		} else {
			plg.addPlayerToTeam(Teams.ONI, p);
			MessageManager.messageAll(p.getName() + "が鬼に選ばれました");

			//装備などつけてテレポート
			p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
			p.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
			p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
			p.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
			p.teleport(plg.getOniPos());
		}
	}

}
