package insaneMC.plugin.event.events;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import insaneMC.plugin.utils.InsaneItems;

public class OnBlockBreak implements Listener {

	public OnBlockBreak(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		ItemStack inHandItem = p.getItemInHand();
		if (InsaneItems.items.contains(inHandItem.getType())) {
			p.getInventory().remove(inHandItem);
			p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
		}
	}
}
