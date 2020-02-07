package com.github.jp.erudo.ebowspleef2.utils;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {

	public ItemStack makeItem(Material m, String name, String desc, int amount) {
		ItemStack item = new ItemStack(m,amount);

		//メタデータ作成
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);

		//説明文作成
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(desc);
		meta.setLore(lore);

		item.setItemMeta(meta);

		return item;
	}

}
