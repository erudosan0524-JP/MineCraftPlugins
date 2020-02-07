package com.github.jp.erudo.ebowspleef2.utils;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class ItemManager {

	public ItemStack makeItem(Material m, String name, String desc, int amount) {
		ItemStack item = new ItemStack(m,amount);

		//メタデータ作成
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + name);

		//説明文作成
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(desc);
		meta.setLore(lore);

		item.setItemMeta(meta);

		return item;
	}

	public ItemStack makeBow(String name, String desc, int amount) {
		ItemStack bow = this.makeItem(Material.BOW, name, desc, amount);
		return bow;
	}

}
