package insaneMC.plugin.event.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class OnEnchant implements Listener {

	public OnEnchant(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onEnchant(EnchantItemEvent e) {
		e.setCancelled(true);
	}
}
