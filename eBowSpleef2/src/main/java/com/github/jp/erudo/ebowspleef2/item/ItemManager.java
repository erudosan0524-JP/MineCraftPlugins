package com.github.jp.erudo.ebowspleef2.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.jp.erudo.ebowspleef2.enums.ArmorType;

import net.md_5.bungee.api.ChatColor;

public class ItemManager {

	public ItemStack makeItem(Material m, String name,int amount,String... descs) {
		ItemStack item = new ItemStack(m, amount);

		//メタデータ作成
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BOLD + name);

		//説明文作成
		ArrayList<String> lore = new ArrayList<String>();
		for(String desc : descs) {
			lore.add(desc);
		}

		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		item.setItemMeta(meta);

		return item;
	}

	public ItemStack makePotion(String name, List<String> lore, PotionEffectType effect1, PotionEffectType effect2,
			PotionEffectType effect3, int level1, int level2, int level3, Color color) {

		ItemStack potion = new ItemStack(Material.SPLASH_POTION, 5);
		PotionMeta potmeta = (PotionMeta) potion.getItemMeta();

		potmeta.setDisplayName(name);

		potmeta.setLore(lore);

		potmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		potmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		potmeta.setColor(color);

		potmeta.addCustomEffect(new PotionEffect(effect1, 10 * 20, level1), false);
		potmeta.addCustomEffect(new PotionEffect(effect2, 10 * 20, level2), false);
		potmeta.addCustomEffect(new PotionEffect(effect3,10 * 20, level3), false);

		potion.setItemMeta(potmeta);

		return potion;
	}

	//helmet,chestplate,leggings,bootsの４つをキーとしたマップを返す
	public HashMap<ArmorType, ItemStack> makeLeatherEquipment(String hel, String chest, String leg, String boot,
			Color color) {
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

		HashMap<ArmorType, ItemStack> map = new HashMap<ArmorType, ItemStack>();
		map.put(ArmorType.HELMET, helmet);
		map.put(ArmorType.CHESTPLATE, chestplate);
		map.put(ArmorType.LEGGINGS, leggings);
		map.put(ArmorType.BOOTS, boots);

		return map;

	}

	public ItemStack makeBow(String name,int amount, String... descs) {
		ItemStack bow = this.makeItem(Material.BOW, name, amount,descs);
		bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
		return bow;
	}
}
