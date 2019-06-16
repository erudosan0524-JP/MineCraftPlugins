package com.github.erudo0524.eoni2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import com.github.erudo0524.eoni2.enums.GameState;
import com.github.erudo0524.eoni2.enums.Teams;

public class CommandManager implements CommandExecutor {

	private Main plg;

	private List<Player> wpPlayerList = new ArrayList<Player>();

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
//		int INIT_SEC = Config.getInstance(plg).getDefaultTime();

		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("start")) {
				if (args[1].length() > 0) {
					int i = 0;
					try {
						i = Integer.parseInt(args[1]);
					} catch (NumberFormatException e) {
					}

					//ゲーム状態をGAMINGに変更
					plg.setCurrentGameState(GameState.GAMING);

					game = new Game(plg, i);
					task = plg.getServer().getScheduler().runTaskTimer(plg, game, 0L, 20L);

					plg.getServer().broadcastMessage(ChatColor.AQUA + "鬼ごっこスタート！");
					game.setTask(task);

					//鬼以外のプレイヤーをPlayerチームに振り分け
					for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						if(!plg.getTeam(Teams.ONI).hasEntry(p.getName())) {
							plg.addPlayerToTeam(Teams.PLAYER, p);
						}
					}

					return true;

				}
			} else if (args[0].equalsIgnoreCase("wp")) {

				//岩盤の上に立っているプレイヤーをリストに入れる
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.BEDROCK) {
						wpPlayerList.add(p);
					}
				}

				//ランダムにリストからプレイヤーを抽出
				Random r = new Random();
				Player wpPlayer = wpPlayerList.get(r.nextInt(wpPlayerList.size()));
				plg.addPlayerToTeam(Teams.ONI, wpPlayer);
				MessageManager.messageAll(wpPlayer.getName() + "が鬼に選ばれました");
				wpPlayer.teleport(plg.getOniPos());

				return true;
			} else if(args[0].equalsIgnoreCase("settppos")) {
				MessageManager.sendMessage(player, "テレポート座標を現在位置に指定しました");
				plg.setTpPos(player.getLocation());
				return true;

			} else if(args[0].equalsIgnoreCase("setonipos")) {
				MessageManager.sendMessage(player, "鬼のTPポイントを現在位置に指定しました");
				plg.setOniPos(player.getLocation());
				return true;
			}
		}
		player.sendMessage("使い方:");
		return false;
	}

}
