package insaneMC.plugin.event;

import org.bukkit.plugin.java.JavaPlugin;

import insaneMC.plugin.event.events.OnBlockBreak;
import insaneMC.plugin.event.events.OnDamageEntity;
import insaneMC.plugin.event.events.OnEnchant;
import insaneMC.plugin.event.events.OnEntitySpawn;

public class EventManager {

	public EventManager(JavaPlugin plg) {
		new OnDamageEntity(plg);
		new OnEnchant(plg);
		new OnBlockBreak(plg);
		new OnEntitySpawn(plg);
	}
}
