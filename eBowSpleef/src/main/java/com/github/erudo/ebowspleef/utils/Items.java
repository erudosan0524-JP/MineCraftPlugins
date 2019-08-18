package com.github.erudo.ebowspleef.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Items {

	private ItemStack Bow;
	private ItemMeta Bowmeta;

	public Items() {
		Bow = new ItemStack(Material.BOW,1);
		Bowmeta = Bow.getItemMeta();
		Bowmeta.setDisplayName(ChatColor.RED + "戦闘用爆発式丸木弓");
		Bowmeta.addEnchant(Enchantment.ARROW_INFINITE, 100, true);
		Bowmeta.addEnchant(Enchantment.KNOCKBACK, 1, false);
		Bowmeta.addEnchant(Enchantment.MENDING, 100, true);
		Bowmeta.addEnchant(Enchantment.DURABILITY, 100, true);
		Bow.setItemMeta(Bowmeta);
	}

	public ItemStack getOriginalBow() {
		return Bow;
	}

}
