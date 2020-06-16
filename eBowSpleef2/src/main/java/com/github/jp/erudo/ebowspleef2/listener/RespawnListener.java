package com.github.jp.erudo.ebowspleef2.listener;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.enums.ArmorType;
import com.github.jp.erudo.ebowspleef2.enums.GameState;
import com.github.jp.erudo.ebowspleef2.enums.Teams;
import com.github.jp.erudo.ebowspleef2.item.ItemManager;
import com.github.jp.erudo.ebowspleef2.utils.PlayersSetting;

public class RespawnListener implements Listener {

	private Main plg;

	public RespawnListener(Main plg) {
		this.plg = plg;
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		if(!(plg.getCurrentGameState() == GameState.GAMING)) {
			return;
		}

		Player player = e.getPlayer();
		ItemManager itemManager = new ItemManager();

		if(plg.getMyConfig().isCanRespawn()) {
			if (plg.getTeam(Teams.BLUE).hasEntry(player.getName())) {
				//テレポート＆スポーン地点設定

				final String teamname = ChatColor.BLUE + "青チーム";
				HashMap<ArmorType, ItemStack> map = new HashMap<ArmorType, ItemStack>();
				map = itemManager.makeLeatherEquipment(teamname + "ヘルメット", teamname + "チェストプレート", teamname + "レギンス",
						teamname + "ブーツ", Color.BLUE);
				player.getInventory().setHelmet(map.get(ArmorType.HELMET));
				player.getInventory().setChestplate(map.get(ArmorType.CHESTPLATE));
				player.getInventory().setLeggings(map.get(ArmorType.LEGGINGS));
				player.getInventory().setBoots(map.get(ArmorType.BOOTS));

				e.setRespawnLocation(PlayersSetting.getBluePos());
				player.setGameMode(GameMode.ADVENTURE);
				player.setSneaking(true);

			//赤チームだったら
			} else if (plg.getTeam(Teams.RED).hasEntry(player.getName())) {

				final String teamname = ChatColor.RED + "赤チーム";
				HashMap<ArmorType, ItemStack> map = new HashMap<ArmorType, ItemStack>();
				map = itemManager.makeLeatherEquipment(teamname + "ヘルメット", teamname + "チェストプレート", teamname + "レギンス",
						teamname + "ブーツ", Color.RED);
				player.getInventory().setHelmet(map.get(ArmorType.HELMET));
				player.getInventory().setChestplate(map.get(ArmorType.CHESTPLATE));
				player.getInventory().setLeggings(map.get(ArmorType.LEGGINGS));
				player.getInventory().setBoots(map.get(ArmorType.BOOTS));

				e.setRespawnLocation(PlayersSetting.getRedPos());
				player.setGameMode(GameMode.ADVENTURE);
				player.setSneaking(true);
			}
		}
	}
}
