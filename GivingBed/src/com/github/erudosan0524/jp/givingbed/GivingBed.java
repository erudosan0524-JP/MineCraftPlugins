package com.github.erudosan0524.jp.givingbed;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GivingBed extends JavaPlugin {

	@Override
	public void onDisable() {
		getLogger().info("プラグインが読み込まれました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが読み込まれました");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}

		Player p = (Player) sender;

		if (command.getName().equalsIgnoreCase("bed")) {
//			if (args.length == 1) {
//				Player player = this.getServer().getPlayer(args[1]);
//
//				if (player == null) {
//
//					p.sendMessage(player.getName() + "というプレイヤーは存在しません！");
//
//					return true;
//
//				}
//
//				player.getInventory().addItem(new ItemStack(Material.BED, 1));
//			}

			p.getInventory().addItem(new ItemStack(Material.BED, 1));
		}
		return false;
	}

}
