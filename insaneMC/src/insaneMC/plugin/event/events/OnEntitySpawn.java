package insaneMC.plugin.event.events;

import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import insaneMC.plugin.utils.Mobs;

public class OnEntitySpawn implements Listener {

	public OnEntitySpawn(JavaPlugin plg) {
		plg.getServer().getPluginManager().registerEvents(this, plg);
	}

	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent e) {
		Entity entity = e.getEntity();
		Random rand = new Random();
		if(entity.getType() == EntityType.CREEPER) {
			Mobs.creepers.add(entity);
			//creepersの中から一つ選択
			int randomNumber = rand.nextInt(Mobs.creepers.size() + 1);
			Mobs.creepers.get(randomNumber).setCustomName(Mobs.creeperName);
			Mobs.creepers.get(randomNumber).setCustomNameVisible(true);
		}

	}
}
