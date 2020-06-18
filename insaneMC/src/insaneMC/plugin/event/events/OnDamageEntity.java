package insaneMC.plugin.event.events;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import insaneMC.plugin.utils.Mobs;

public class OnDamageEntity implements Listener {

	public OnDamageEntity(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player)) {
			return;
		}

		Player player = (Player) e.getDamager();
		Entity entity = e.getEntity();

		if (entity.getType() == EntityType.SILVERFISH) {
			player.getWorld().spawnEntity(entity.getLocation(), EntityType.SILVERFISH);
		}

		if (entity.getType() == EntityType.CREEPER && entity.getCustomName() == Mobs.creeperName) {
			if (player.getItemInHand().getType() == Material.STONE_SWORD
					|| player.getItemInHand().getType() == Material.WOOD_SWORD) {
				player.getWorld().createExplosion(entity.getLocation(), 3F);
			}
		}
	}
}
