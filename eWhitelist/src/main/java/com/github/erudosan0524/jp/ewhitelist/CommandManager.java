package com.github.erudosan0524.jp.ewhitelist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager implements CommandExecutor {

	JavaPlugin plg;

	public CommandManager(JavaPlugin plg) {
		this.plg = plg;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if (sender instanceof Player) {

			Player player = (Player) sender;

			if (args.length != 0) {
				if (args[0].equalsIgnoreCase("addall")) {
					if (!player.hasPermission("ewhitelist.addall")) {
						player.sendMessage(ChatColor.RED + "[eWhitelist]" + "権限を持っていないためこのコマンドを実行できません");
						return true;
					}

					List<String> datas = loadFile(new File(plg.getDataFolder() + "\\IDlist.yml"));
					for (String s : datas) {
						plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(), "whitelist add " + s);
					}
					EWhitelist.sendMessage(player, "ファイル内のすべてのプレイヤーをホワイトリストに追加しました");
					return true;

				} else if (args[0].equalsIgnoreCase("removeall")) {
					if (!player.hasPermission("ewhitelist.removeall")) {
						player.sendMessage(ChatColor.RED + "[eWhitelist]" + "権限を持っていないためこのコマンドを実行できません");
						return true;
					}

					for (OfflinePlayer p_ : plg.getServer().getWhitelistedPlayers()) {
						plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
								"whitelist remove " + p_.getName());
					}

					EWhitelist.sendMessage(player, "すべてのプレイヤーをホワイトリストから削除しました");
					return true;

				} else if (args[0].equalsIgnoreCase("list")) {
					if (!player.hasPermission("ewhitelist.list")) {
						player.sendMessage(ChatColor.RED + "[eWhitelist]" + "権限を持っていないためこのコマンドを実行できません");
						return true;
					}

					player.sendMessage(ChatColor.YELLOW + "===ホワイトリスト一覧===");

					for (OfflinePlayer p : plg.getServer().getWhitelistedPlayers()) {
						player.sendMessage("・ " + p.getName());
					}

					return true;
				} else if (args[0].equalsIgnoreCase("add")) {
					if (!player.hasPermission("ewhitelist.add")) {
						player.sendMessage(ChatColor.RED + "[eWhitelist]" + "権限を持っていないためこのコマンドを実行できません");
						return true;
					}

					if (args[1].length() != 0) {
						plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(), "whitelist add " + args[1]);
						EWhitelist.sendMessage(player, args[1] + "をホワイトリストに追加しました");
					} else {
						EWhitelist.sendMessage(player, "プレイヤーを指定してください");
						return true;
					}
				} else if (args[0].equalsIgnoreCase("remove")) {
					if (!player.hasPermission("ewhitelist.remove")) {
						player.sendMessage(ChatColor.RED + "[eWhitelist]" + "権限を持っていないためこのコマンドを実行できません");
						return true;
					}

					if (args[1].length() != 0) {
						plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
								"whitelist remove " + args[1]);
						EWhitelist.sendMessage(player, args[1] + "をホワイトリストから削除しました");
						return true;
					} else {
						EWhitelist.sendMessage(player, "プレイヤーを指定してください");
						return true;
					}
				}
			}

			player.sendMessage(ChatColor.YELLOW + "===コマンド一覧===\n"
					+ ChatColor.WHITE + "・/ewhitelist addall\n"
					+ ChatColor.GRAY + "ファイル内のすべてのプレイヤーをホワイトリストに登録\n"
					+ ChatColor.WHITE + "・/ewhitelist removeall\n"
					+ ChatColor.GRAY + "すべてのプレイヤーをホワイトリストから削除\n"
					+ ChatColor.WHITE + "・/ewhitelist list\n"
					+ ChatColor.GRAY + "ホワイトリストに登録されているプレイヤー一覧の確認\n"
					+ ChatColor.WHITE + "・/ewhitelist add [プレイヤー名]\n"
					+ ChatColor.GRAY + "指定プレイヤーをホワイトリストに登録\n"
					+ ChatColor.WHITE + "・/ewhitelist remove [プレイヤー名]\n"
					+ ChatColor.GRAY + "指定プレイヤーをホワイトリストから削除");
			return true;
		} else {
			if (args.length != 0) {
				if (args[0].equalsIgnoreCase("addall")) {
					List<String> datas = loadFile(new File(plg.getDataFolder() + "\\IDlist.yml"));
					for (String s : datas) {
						plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(), "whitelist add " + s);
					}
					System.out.println("ファイル内のすべてのプレイヤーをホワイトリストに追加しました");
					return true;

				} else if (args[0].equalsIgnoreCase("removeall")) {
					for (OfflinePlayer p_ : plg.getServer().getWhitelistedPlayers()) {
						plg.getServer().dispatchCommand(plg.getServer().getConsoleSender(),
								"whitelist remove " + p_.getName());
					}
					System.out.println("すべてのプレイヤーをホワイトリストから削除しました");
					return true;
				}
			}
		}
		return false;
	}

	private List<String> loadFile(File file) {
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));) {
			String str;
			List<String> lines = new ArrayList<>();
			while ((str = reader.readLine()) != null) {
				lines.add(str);
			}
			return lines;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
