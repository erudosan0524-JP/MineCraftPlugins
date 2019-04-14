package com.github.efly.erudo;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static HashMap<UUID,Boolean> isFlying = new HashMap<>();

	Player target = null;

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが読み込まれました");

		new EventListener(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		target = (Player) sender;
		Location targetLoc = target.getLocation();
		World Loc = targetLoc.getWorld();

		if (args.length != 0) {
			if (target.getGameMode().equals(GameMode.SURVIVAL)) {
				if (args[0].equalsIgnoreCase("on")) {
					if(!target.hasPermission("efly.on")) {
						return true;
					}
					setFlght(target, true);
					Main.isFlying.put(target.getUniqueId(), true);

					Loc.playSound(targetLoc, Sound.BLOCK_ANVIL_PLACE, 1, 1);
					sender.sendMessage("§a[eFly] flyを有効にしました");
					return true;
				}
				if (args[0].equalsIgnoreCase("off")) {
					if(!target.hasPermission("efly.off")) {
						return true;
					}
					setFlght(target, false);
					Main.isFlying.remove(target.getUniqueId());

					Loc.playSound(targetLoc, Sound.BLOCK_ANVIL_PLACE, 1, 1);
					sender.sendMessage("§a[eFly] flyを無効にしました");
					return true;
				}

			} else {
				sender.sendMessage("§c[eFly]このコマンドはサバイバルモードでのみ実行できます");
				return true;
			}

		}

		sender.sendMessage("§c[eFly]使い方： /efly on または off");
		return false;
	}

	public void setFlght(Player p, boolean b) {
		p.setFlying(b);
		p.setAllowFlight(b);
		p.setFlySpeed(0.1F);
	}
}
