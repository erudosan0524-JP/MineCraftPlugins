package janken.plugin.runnable;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import janken.plugin.utils.User;

public class Repeater extends BukkitRunnable {
	BukkitTask task;
	Player player;
	User user = new User();

	public Repeater(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		if (user.tehuda.size() >= 2) {
			player.sendMessage("ほかの2人がじゃんけんをしているようです。");
		}
	}

	public void settask(BukkitTask task) {
		this.task = task;
	}
}
