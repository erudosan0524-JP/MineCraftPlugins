package com.github.jp.erudo.edisablespawnegg;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public class Items {

	private List<Material> spawnEggs = new ArrayList<Material>();

	public Items() {
		load();
	}

	public void load() {
		//spawnegg
		spawnEggs.add(Material.BAT_SPAWN_EGG);
		spawnEggs.add(Material.BEE_SPAWN_EGG);
		spawnEggs.add(Material.BLAZE_SPAWN_EGG);
		spawnEggs.add(Material.CAT_SPAWN_EGG);
		spawnEggs.add(Material.CAVE_SPIDER_SPAWN_EGG);
		spawnEggs.add(Material.CHICKEN_SPAWN_EGG);
		spawnEggs.add(Material.COD_SPAWN_EGG);
		spawnEggs.add(Material.COW_SPAWN_EGG);
		spawnEggs.add(Material.CREEPER_SPAWN_EGG);
		spawnEggs.add(Material.DOLPHIN_SPAWN_EGG);
		spawnEggs.add(Material.DONKEY_SPAWN_EGG);
		spawnEggs.add(Material.DROWNED_SPAWN_EGG);
		spawnEggs.add(Material.ELDER_GUARDIAN_SPAWN_EGG);
		spawnEggs.add(Material.ENDERMAN_SPAWN_EGG);
		spawnEggs.add(Material.ENDERMITE_SPAWN_EGG);
		spawnEggs.add(Material.EVOKER_SPAWN_EGG);
		spawnEggs.add(Material.FOX_SPAWN_EGG);
		spawnEggs.add(Material.GHAST_SPAWN_EGG);
		spawnEggs.add(Material.GUARDIAN_SPAWN_EGG);
		spawnEggs.add(Material.HORSE_SPAWN_EGG);
		spawnEggs.add(Material.HUSK_SPAWN_EGG);
		spawnEggs.add(Material.LLAMA_SPAWN_EGG);
		spawnEggs.add(Material.MAGMA_CUBE_SPAWN_EGG);
		spawnEggs.add(Material.MOOSHROOM_SPAWN_EGG);
		spawnEggs.add(Material.MULE_SPAWN_EGG);
		spawnEggs.add(Material.OCELOT_SPAWN_EGG);
		spawnEggs.add(Material.PANDA_SPAWN_EGG);
		spawnEggs.add(Material.PARROT_SPAWN_EGG);
		spawnEggs.add(Material.PHANTOM_SPAWN_EGG);
		spawnEggs.add(Material.PIG_SPAWN_EGG);
		spawnEggs.add(Material.PILLAGER_SPAWN_EGG);
		spawnEggs.add(Material.POLAR_BEAR_SPAWN_EGG);
		spawnEggs.add(Material.PUFFERFISH_SPAWN_EGG);
		spawnEggs.add(Material.RABBIT_SPAWN_EGG);
		spawnEggs.add(Material.RAVAGER_SPAWN_EGG);
		spawnEggs.add(Material.SALMON_SPAWN_EGG);
		spawnEggs.add(Material.SHEEP_SPAWN_EGG);
		spawnEggs.add(Material.SHULKER_SPAWN_EGG);
		spawnEggs.add(Material.SILVERFISH_SPAWN_EGG);
		spawnEggs.add(Material.SKELETON_HORSE_SPAWN_EGG);
		spawnEggs.add(Material.SKELETON_SPAWN_EGG);
		spawnEggs.add(Material.SLIME_SPAWN_EGG);
		spawnEggs.add(Material.SPIDER_SPAWN_EGG);
		spawnEggs.add(Material.SQUID_SPAWN_EGG);
		spawnEggs.add(Material.STRAY_SPAWN_EGG);
		spawnEggs.add(Material.TRADER_LLAMA_SPAWN_EGG);
		spawnEggs.add(Material.TROPICAL_FISH_SPAWN_EGG);
		spawnEggs.add(Material.TURTLE_SPAWN_EGG);
		spawnEggs.add(Material.VEX_SPAWN_EGG);
		spawnEggs.add(Material.VILLAGER_SPAWN_EGG);
		spawnEggs.add(Material.VINDICATOR_SPAWN_EGG);
		spawnEggs.add(Material.WANDERING_TRADER_SPAWN_EGG);
		spawnEggs.add(Material.WITCH_SPAWN_EGG);
		spawnEggs.add(Material.WITHER_SKELETON_SPAWN_EGG);
		spawnEggs.add(Material.WOLF_SPAWN_EGG);
		spawnEggs.add(Material.ZOMBIE_HORSE_SPAWN_EGG);
		spawnEggs.add(Material.ZOMBIE_PIGMAN_SPAWN_EGG);
		spawnEggs.add(Material.ZOMBIE_SPAWN_EGG);
		spawnEggs.add(Material.ZOMBIE_VILLAGER_SPAWN_EGG);

	}

	public List<Material> getSpawnEggs() {
		return spawnEggs;
	}

}
