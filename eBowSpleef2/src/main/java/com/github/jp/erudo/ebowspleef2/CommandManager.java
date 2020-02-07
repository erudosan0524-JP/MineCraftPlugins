package com.github.jp.erudo.ebowspleef2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import com.github.jp.erudo.ebowspleef2.enums.Teams;
import com.github.jp.erudo.ebowspleef2.utils.MessageManager;
import com.github.jp.erudo.ebowspleef2.utils.PlayersSetting;

public class CommandManager implements CommandExecutor {

	private Main plg;

	public CommandManager(Main plg) {
		this.plg = plg;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		Player player = (Player) sender;

		if (args.length <= 0) {
			return false;
		}

		if (args[0].equalsIgnoreCase("start")) {

		} else if (args[0].equalsIgnoreCase("wp")) {
			MessageManager.messageAll("チームの振り分けを開始します・・・");
			List<Player> wpPlayerList = new ArrayList<Player>();

			//岩盤の上に立っているプレイヤーをリストに入れる
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.BEDROCK) {
					wpPlayerList.add(p);
				}
			}

			Collections.shuffle(wpPlayerList);

			try {
				//シャッフルされたリストから交互にチーム分け
				Player wpPlayer;
				for(int i=0; i < wpPlayerList.size(); i++) {
					wpPlayer = wpPlayerList.get(i);
					if((i/2) % 2 == 0) {
						PlayersSetting.addPlayerToTeam(Teams.RED,wpPlayer);
					} else {
						PlayersSetting.addPlayerToTeam(Teams.BLUE,wpPlayer);
					}
				}
				MessageManager.messageAll("チームの振り分けが完了しました");
			} catch (IllegalArgumentException e) {
				MessageManager.sendMessage(player, "岩盤の上にいるプレイヤーが見つかりませんでした");
			}

			return true;
		} else if (args[0].equalsIgnoreCase("redwp")) {

		} else if (args[0].equalsIgnoreCase("bluewp")) {

		} else if (args[0].equalsIgnoreCase("setredpos")) {

		} else if (args[0].equalsIgnoreCase("setbluepos")) {

		} else if (args[0].equalsIgnoreCase("setlobbypos")) {

		} else if (args[0].equalsIgnoreCase("version")) {
			PluginDescriptionFile pdf = plg.getDescription();
			MessageManager.sendMessage(player, "Version: " + pdf.getVersion());
			return true;
		}
		MessageManager.CommandContent(player);
		return false;
	}

}
