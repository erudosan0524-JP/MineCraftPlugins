package com.github.erudosan0524jp.jikochu.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Inventory {

	public static void setHelmet(Player player, Material material) {
		ItemStack helmet = new ItemStack(material);
		player.getInventory().setHelmet(helmet);
		update(player);
	}

	public static void setArmor(Player player, Material material) {
		ItemStack armor = new ItemStack(material);
		player.getInventory().setChestplate(armor);
		update(player);
	}

	public static void setLeggings(Player player, Material material) {
		ItemStack leggings = new ItemStack(material);
		player.getInventory().setLeggings(leggings);
		update(player);
	}

	public static void setBoots(Player player, Material material) {
		ItemStack boots = new ItemStack(material);
		player.getInventory().setBoots(boots);
		update(player);
	}

	private static void update(Player player) {
		player.updateInventory();
	}

}
