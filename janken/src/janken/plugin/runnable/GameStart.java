package janken.plugin.runnable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import janken.plugin.utils.User;

public class GameStart extends BukkitRunnable{

	BukkitTask task;
	JavaPlugin plg;
	String p1;
	String p2;
	User user = new User();

	public GameStart(JavaPlugin plg) {
		this.plg = plg;
	}

	@Override
	public void run() {
		int time = 5;

		if (time == 5) {
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				p.sendMessage("じゃんけ～ん・・・");
			}
			time--;
		} else if (time <= 0) {
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				p.sendMessage("ぽんっ！");
			}
			new BukkitRunnable() {

				@SuppressWarnings("unlikely-arg-type")
				@Override
				public void run() {
					int time = 5;

					if(time == 5) {
						for(Player p : Bukkit.getServer().getOnlinePlayers()) {
							p.sendMessage(ChatColor.YELLOW + "＝＝＝結果表示＝＝＝");
						}
						time--;
					}else if(time == 3) {
						if(user.tehuda.get(0) == 1) {
							p1 = "グー";
						}else if(user.tehuda.get(0) == 2) {
							p1 = "チョキ";
						}else if(user.tehuda.get(0) == 3) {
							p1 = "パー";
						}
						for(Player p : Bukkit.getServer().getOnlinePlayers()) {
							p.sendMessage(ChatColor.GREEN + p1);
						}
						time--;
					}else if(time <= 0) {
						if(user.tehuda.get(1) == 1) {
							p2 = "グー";
						}else if(user.tehuda.get(1) == 2) {
							p2 = "チョキ";
						}else if(user.tehuda.get(1) == 3) {
							p2 = "パー";
						}
						for(Player p : Bukkit.getServer().getOnlinePlayers()) {
							p.sendMessage(ChatColor.GREEN + p2);
						}

						user.tehuda.clear();
					}
				}

			}.runTaskTimer(plg, 0L, 20L);
		}
		time--;
	}

	public void settask(BukkitTask task) {
		this.task = task;
	}

}
