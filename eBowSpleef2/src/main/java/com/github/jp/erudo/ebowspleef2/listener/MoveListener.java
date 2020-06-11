package com.github.jp.erudo.ebowspleef2.listener;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.enums.GameState;
import com.github.jp.erudo.ebowspleef2.enums.Teams;
import com.github.jp.erudo.ebowspleef2.item.Items;

import net.md_5.bungee.api.ChatColor;

public class MoveListener implements Listener {

	private Main plg;

	public MoveListener(Main plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e) {

		Player player = e.getPlayer();
		Block downBlock = player.getLocation().getBlock().getRelative(BlockFace.DOWN);

		if(downBlock.getType() == Material.CONCRETE) {
			if(!(plg.getCurrentGameState() == GameState.GAMING)) return;

			if(downBlock.getData() == 14 || downBlock.getData() == 11) { //赤コンクリートまたは青コンクリート
				player.damage(3); //♡1.5
			}
		}

		if(plg.getCurrentGameState() == GameState.GAMING) {
			if(player.getInventory().contains(Material.BOW)) {
				ItemStack item = player.getInventory().getItem(player.getInventory().first(Material.BOW));

				if(item != null && item.getType() == Material.BOW && item.hasItemMeta()) {
					if(ChatColor.stripColor(item.getItemMeta().getDisplayName().toString()).equals(ChatColor.stripColor(Items.bow2Name))) {
						if(downBlock.getType() == Material.WOOL) {
							downBlock.setType(Material.WOOL);

							if(plg.getTeam(Teams.BLUE).hasEntry(player.getName())) {
								downBlock.setData(DyeColor.BLUE.getWoolData());
							} else if(plg.getTeam(Teams.RED).hasEntry(player.getName())) {
								downBlock.setData(DyeColor.RED.getWoolData());
							}
						}
					}
				}
			}




		}

	}

}
