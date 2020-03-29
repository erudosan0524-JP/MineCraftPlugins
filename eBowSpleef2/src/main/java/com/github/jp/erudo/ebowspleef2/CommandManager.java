package com.github.jp.erudo.ebowspleef2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.scheduler.BukkitTask;

import com.github.jp.erudo.ebowspleef2.enums.ArmorType;
import com.github.jp.erudo.ebowspleef2.enums.GameState;
import com.github.jp.erudo.ebowspleef2.enums.Teams;
import com.github.jp.erudo.ebowspleef2.item.ItemManager;
import com.github.jp.erudo.ebowspleef2.runnable.Game;
import com.github.jp.erudo.ebowspleef2.utils.MessageManager;
import com.github.jp.erudo.ebowspleef2.utils.PlayersSetting;
import com.github.jp.erudo.ebowspleef2.utils.WorldManager;

public class CommandManager implements CommandExecutor {

	private Main plg;
	private Game game;
	private BukkitTask task;

	public CommandManager(Main plg) {
		this.plg = plg;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		Player player = (Player) sender;

		if (args.length <= 0) {
			return false;
		}

		if (args[0].equalsIgnoreCase("start")) {
			if (!player.hasPermission("ebs.commands.start")) {
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
				int i = plg.getMyConfig().getDefaultTime();

				try {
					gameStart(i, player);
				} catch (NullPointerException e) {
					MessageManager.sendMessage(player, "Nullが検知されました。");
				}

				return true;

			}
		} else if (args[0].equalsIgnoreCase("set")) {
			if (!player.hasPermission("ebs.commands.set")) {
				return true;
			}

			MessageManager.messageAll("チームの振り分けを開始します・・・");
			List<Player> wpPlayerList = new ArrayList<Player>();

			//岩盤の上に立っているプレイヤーをリストに入れる
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.BEDROCK) {
					wpPlayerList.add(p);
				}
			}

			Collections.shuffle(wpPlayerList);

			//シャッフルされたリストから交互にチーム分け
			Player wpPlayer;
			for (int i = 0; i < wpPlayerList.size(); i++) {
				wpPlayer = wpPlayerList.get(i);
				System.out.println(wpPlayer);
				if (i % 2 == 0) {
					PlayersSetting.addPlayerToTeam(Teams.RED, wpPlayer);
				} else {
					PlayersSetting.addPlayerToTeam(Teams.BLUE, wpPlayer);
				}
			}
			MessageManager.messageAll("チームの振り分けが完了しました");

			return true;
		} else if (args[0].equalsIgnoreCase("setred")) {
			if (!player.hasPermission("ebs.commands.setred")) {
				return true;
			}

			if (args.length > 1) {
				if (Objects.isNull(plg.getServer().getPlayer(args[1]))) {
					MessageManager.sendMessage(player, "指定したプレイヤーは存在しません");
				} else {
					Player p = plg.getServer().getPlayer(args[1]);
					PlayersSetting.addPlayerToTeam(Teams.RED, p);
					MessageManager.sendMessage(player,
							p.getName() + "を" + ChatColor.RED + "赤チーム" + ChatColor.WHITE + "に設定しました");
					return true;
				}
			}
		} else if (args[0].equalsIgnoreCase("setblue")) {
			if (!player.hasPermission("ebs.commands.setblue")) {
				return true;
			}

			if (Objects.isNull(plg.getServer().getPlayer(args[1]))) {
				MessageManager.sendMessage(player, "指定したプレイヤーは存在しません");
			} else {
				Player p = plg.getServer().getPlayer(args[1]);
				PlayersSetting.addPlayerToTeam(Teams.BLUE, p);
				MessageManager.sendMessage(player,
						p.getName() + "を" + ChatColor.BLUE + "青チーム" + ChatColor.WHITE + "に設定しました");
				return true;
			}
		} else if(args[0].equalsIgnoreCase("setspe")){
			if(!player.hasPermission("ebs.commands.setspe")) {
				return true;
			}

			if (Objects.isNull(plg.getServer().getPlayer(args[1]))) {
				MessageManager.sendMessage(player, "指定したプレイヤーは存在しません");
			} else {
				Player p = plg.getServer().getPlayer(args[1]);
				PlayersSetting.addPlayerToTeam(Teams.SPECTATOR, p);
				MessageManager.sendMessage(player,
						p.getName() + "を" + ChatColor.GRAY + "スペクテイターチーム" + ChatColor.WHITE + "に設定しました");
				return true;
			}
		} else if (args[0].equalsIgnoreCase("setredpos")) {
			if (!player.hasPermission("ebs.commands.redpos")) {
				return true;
			}

			MessageManager.sendMessage(player, "赤チームのスポーン位置を現在位置に指定しました");
			PlayersSetting.setRedPos(player.getLocation());
			return true;
		} else if (args[0].equalsIgnoreCase("setbluepos")) {
			if (!player.hasPermission("ebs.commands.bluepos")) {
				return true;
			}

			MessageManager.sendMessage(player, "青チームのスポーン位置を現在位置に指定しました");
			PlayersSetting.setBluePos(player.getLocation());
			return true;
		} else if (args[0].equalsIgnoreCase("setlobbypos")) {
			if (!player.hasPermission("ebs.commands.lobbypos")) {
				return true;
			}

			MessageManager.sendMessage(player, "ロビーの位置を現在位置に指定しました");
			PlayersSetting.setLobbyPos(player.getLocation());
			return true;
		} else if (args[0].equalsIgnoreCase("shopper")) {
			if(!player.hasPermission("ebs.commands.shopper")) {
				return true;
			}

			Location l = new Location(null, player.getLocation().getX(), player.getLocation().getY() -1, player.getLocation().getZ());
			Villager villager = (Villager) player.getWorld().spawnEntity(l,EntityType.VILLAGER);
			villager.setAI(false);
			villager.setGlowing(true);
			villager.setAdult();
			villager.setCanPickupItems(false);
			villager.setCustomName(ChatColor.GREEN + "SettingVillager");
			villager.setCustomNameVisible(true);
			villager.setGravity(true);
			MessageManager.sendMessage(player, "設定用の村人を召喚しました");

			return true;

		}else if (args[0].equalsIgnoreCase("setstage")) {
			if (Objects.isNull(args[1])) {
				return true;
			} else {
				plg.setStageName(args[1]);
				MessageManager.sendMessage(player, "ステージを" + ChatColor.AQUA + plg.getStageName() + ChatColor.WHITE + "に設定しました");
				return true;
			}
		}else if(args[0].equalsIgnoreCase("build")) {
			MessageManager.messageAll("ワールドを復元中です・・・");
			WorldManager wm = new WorldManager();
			Location loc = new Location(player.getWorld(), plg.getMyConfig().getBeginCoordinate()[0], plg.getMyConfig().getBeginCoordinate()[1], plg.getMyConfig().getBeginCoordinate()[2]);
			wm.loadSchematic(loc, plg.getStageName());
			MessageManager.messageAll("復元が完了しました！");
			return true;

		} else if (args[0].equalsIgnoreCase("resetscore")){
			MessageManager.sendMessage(player, "スコアボードをリセットします");
			plg.setBluePoint(0);
			plg.setRedPoint(0);
			MessageManager.sendMessage(player, "完了しました");
			return true;

		} else if(args[0].equalsIgnoreCase("reload")){
			plg.getMyConfig().reload();
			return true;

		} else if (args[0].equalsIgnoreCase("version")) {
			if (!player.hasPermission("ebs.commands.version")) {
				return true;
			}

			PluginDescriptionFile pdf = plg.getDescription();
			MessageManager.sendMessage(player, "Version: " + pdf.getVersion());
			return true;

		} else if(args[0].equalsIgnoreCase("help")) {
			MessageManager.CommandContent(player);
			return true;
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	private void gameStart(int time, Player player) {
		//ゲーム状態をGAMINGに変更
		plg.setCurrentGameState(GameState.GAMING);

		//アイテム作成用
		ItemManager itemManager = new ItemManager();

		if (!player.getWorld().getPVP()) {
			player.getWorld().setPVP(true);
		}

		if (Objects.isNull(PlayersSetting.getRedPos()) || Objects.isNull(PlayersSetting.getBluePos())
				|| Objects.isNull(PlayersSetting.getLobbyPos())) {
			PlayersSetting.setRedPos(plg.getMyConfig().getRedPosition(player.getWorld()));
			PlayersSetting.setBluePos(plg.getMyConfig().getBluePosition(player.getWorld()));
			PlayersSetting.setLobbyPos(plg.getMyConfig().getLobbyPosition(player.getWorld()));
		}

		game = new Game(plg, time);
		task = plg.getServer().getScheduler().runTaskTimer(plg, game, 0L, 20L);

		MessageManager.broadcastMessage("試合開始！！");
		game.setTask(task);

		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			//青チームだったら
			if (plg.getTeam(Teams.BLUE).hasEntry(p.getName())) {
				//テレポート＆スポーン地点設定
				p.teleport(PlayersSetting.getBluePos());
				p.setBedSpawnLocation(PlayersSetting.getBluePos());

				p.setGameMode(GameMode.ADVENTURE);
				p.setSneaking(true);

				//皮装備装着
				final String teamname = ChatColor.BLUE + "青チーム";
				HashMap<ArmorType, ItemStack> map = new HashMap<ArmorType, ItemStack>();
				map = itemManager.makeLeatherEquipment(teamname + "ヘルメット", teamname + "チェストプレート", teamname + "レギンス",
						teamname + "ブーツ", Color.BLUE);
				p.getInventory().setHelmet(map.get(ArmorType.HELMET));
				p.getInventory().setChestplate(map.get(ArmorType.CHESTPLATE));
				p.getInventory().setLeggings(map.get(ArmorType.LEGGINGS));
				p.getInventory().setBoots(map.get(ArmorType.BOOTS));

				//赤チームだったら
			} else if (plg.getTeam(Teams.RED).hasEntry(p.getName())) {
				p.teleport(PlayersSetting.getRedPos());
				p.setBedSpawnLocation(PlayersSetting.getRedPos());

				p.setGameMode(GameMode.ADVENTURE);
				p.setSneaking(true);

				//皮装備装着
				final String teamname = ChatColor.RED + "赤チーム";
				HashMap<ArmorType, ItemStack> map = new HashMap<ArmorType, ItemStack>();
				map = itemManager.makeLeatherEquipment(teamname + "ヘルメット", teamname + "チェストプレート", teamname + "レギンス",
						teamname + "ブーツ", Color.RED);
				p.getInventory().setHelmet(map.get(ArmorType.HELMET));
				p.getInventory().setChestplate(map.get(ArmorType.CHESTPLATE));
				p.getInventory().setLeggings(map.get(ArmorType.LEGGINGS));
				p.getInventory().setBoots(map.get(ArmorType.BOOTS));
			} else {
				PlayersSetting.addPlayerToTeam(Teams.SPECTATOR, p);
				p.setGameMode(GameMode.SPECTATOR);
			}
		}
	}

}
