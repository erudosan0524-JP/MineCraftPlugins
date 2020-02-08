package com.github.jp.erudo.ebowspleef2.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.github.jp.erudo.ebowspleef2.enums.ArmorType;

import net.md_5.bungee.api.ChatColor;

public class ItemManager {

	public ItemStack makeItem(Material m, String name, String desc, int amount) {
		ItemStack item = new ItemStack(m, amount);

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

	public ItemStack makeItem(Material m, String name, List<String> lore, int amount) {
		ItemStack item = new ItemStack(m, amount);

		//メタデータ作成
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + name);

		//説明文作成
		meta.setLore(lore);

		item.setItemMeta(meta);

		return item;
	}

	//helmet,chestplate,leggings,bootsの４つをキーとしたマップを返す
	public HashMap<ArmorType,ItemStack> makeLeatherEquipment(String hel, String chest, String leg, String boot,Color color) {
		ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
		ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);

		LeatherArmorMeta helmetlm = (LeatherArmorMeta) helmet.getItemMeta();
		LeatherArmorMeta chestlm = (LeatherArmorMeta) chestplate.getItemMeta();
		LeatherArmorMeta leglm = (LeatherArmorMeta) leggings.getItemMeta();
		LeatherArmorMeta bootslm = (LeatherArmorMeta) boots.getItemMeta();

		helmetlm.setDisplayName(hel);
		chestlm.setDisplayName(chest);
		leglm.setDisplayName(leg);
		bootslm.setDisplayName(boot);

		helmetlm.setColor(color);
		chestlm.setColor(color);
		leglm.setColor(color);
		bootslm.setColor(color);

		helmet.setItemMeta(helmetlm);
		chestplate.setItemMeta(chestlm);
		leggings.setItemMeta(leglm);
		boots.setItemMeta(bootslm);

		HashMap<ArmorType,ItemStack> map = new HashMap<ArmorType,ItemStack>();
		map.put(ArmorType.HELMET, helmet);
		map.put(ArmorType.CHESTPLATE, chestplate);
		map.put(ArmorType.LEGGINGS, leggings);
		map.put(ArmorType.BOOTS, boots);

		return map;

	}

	public ItemStack makeBow(String name, String desc, int amount) {
		ItemStack bow = this.makeItem(Material.BOW, name, desc, amount);
		return bow;
	}

}
