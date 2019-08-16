package com.github.erudo0524.eoni2;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Score;

import com.github.erudo0524.eoni2.enums.GameState;
import com.github.erudo0524.eoni2.enums.Teams;
import com.github.erudo0524.eoni2.utils.MessageManager;

public class Game extends BukkitRunnable {

	private final Main plg;
	private BukkitTask task;
	private int count;

	public Game(Main _plg, int _count) {
		this.plg = _plg;
		this.count = _count;
	}

	public void run() {

		if (plg.getCurrentGameState() == GameState.END) {
			MessageManager.broadcastMessage("鬼ごっこ終了!");
			count = 0;
			for (Player player : Bukkit.getServer().getOnlinePlayers()) {
				player.setSneaking(false);

				if (player.getWorld().getPVP()) {
					continue;
				} else {
					player.getWorld().setPVP(false);
				}
			}
			plg.getServer().getScheduler().cancelTask(task.getTaskId());
		}

		if (plg.getCurrentGameState() == GameState.GAMING) {
			if (count > 0) {
				Score TimeScore = plg.getObj().getScore(ChatColor.GOLD + "残り時間: ");
				TimeScore.setScore(count);

				Score PlayersNumScore = plg.getObj().getScore(ChatColor.DARK_BLUE + "プレイヤー人数: ");
				PlayersNumScore.setScore(plg.getTeam(Teams.PLAYER).getEntries().size());

				Score OnisNumScore = plg.getObj().getScore(ChatColor.DARK_RED + "鬼人数: ");
				OnisNumScore.setScore(+plg.getTeam(Teams.ONI).getEntries().size());

				//サプライチェスト設定
				if (count % plg.getSupplyChestInterval() == 0) {
					Random r = new Random();

					PotionEffectType[] enabledPotionTypes = { PotionEffectType.JUMP, PotionEffectType.INVISIBILITY,
							PotionEffectType.SPEED, PotionEffectType.SLOW };
					ItemStack item = new ItemStack(Material.SPLASH_POTION, 1);
					PotionMeta meta = (PotionMeta) item.getItemMeta();
					meta.setDisplayName(ChatColor.DARK_PURPLE + "特殊なポーション");
					meta.clearCustomEffects();
					meta.addCustomEffect(
							new PotionEffect(enabledPotionTypes[r.nextInt(enabledPotionTypes.length)], 5 * 20, 1),
							false);
					item.setItemMeta(meta);


					Block block = plg.getSupplyChestPos().get(r.nextInt(plg.getSupplyChestPos().size()))
							.getBlock();
					Chest SupplyChest = null;
					if(block.getType().equals(Material.CHEST)) {
						if(!(block instanceof DoubleChest)) {
							SupplyChest = (Chest) plg.getSupplyChestPos().get(r.nextInt(plg.getSupplyChestPos().size()))
									.getBlock().getState();
						}
					} else {
						MessageManager.messageAll("SupplyChestの中にチェストでないものがあったためゲームを終了します");
						plg.setCurrentGameState(GameState.END);
					}
					SupplyChest.getInventory().addItem(item);
					SupplyChest.getLocation().getWorld().spawnParticle(Particle.VILLAGER_HAPPY,
							SupplyChest.getLocation(), 20, 1, 1, 1);
					SupplyChest.getLocation().getWorld().playSound(SupplyChest.getLocation(),
							Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
					MessageManager.messageAll("サプライチェストにアイテムが追加された。");

				}

				if (plg.getTeam(Teams.PLAYER).getEntries().size() <= 0) {
					plg.setCurrentGameState(GameState.END);
					return;
				}

				if (plg.getTeam(Teams.ONI).getEntries().size() <= 0) {
					plg.setCurrentGameState(GameState.END);
					MessageManager.messageAll("鬼が０人だったため試合が終了しました");
					return;
				}

			} else {
				plg.setCurrentGameState(GameState.END);
			}
			count--;
		}
	}

	public void setTask(BukkitTask task) {
		this.task = task;
	}

}
