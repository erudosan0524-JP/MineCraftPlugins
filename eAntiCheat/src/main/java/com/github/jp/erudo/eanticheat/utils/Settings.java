package com.github.jp.erudo.eanticheat.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public class Settings {

	//SPEED Settings
	public static final Double MAX_XZ_SPEED = 0.66D;

	public static final Double MAX_XZ_EATING_SPEED = 0.10178D;

	public static final Double MAX_XZ_BLOCKING_SPEED = 0.10224D;


	//Alert Permission
	public static final String ALERT = "eac.alert";

	public static final List<Material> FOODS;
	static {
		FOODS = new ArrayList<Material>();

		FOODS.add(Material.RAW_CHICKEN);
		FOODS.add(Material.COOKED_CHICKEN);
		FOODS.add(Material.RAW_BEEF);
		FOODS.add(Material.COOKED_BEEF);
		FOODS.add(Material.MUTTON);
		FOODS.add(Material.COOKED_MUTTON);
		FOODS.add(Material.PORK);
		FOODS.add(Material.GRILLED_PORK);
		FOODS.add(Material.COOKED_RABBIT);
		FOODS.add(Material.RABBIT_STEW);
		FOODS.add(Material.RAW_FISH);
		FOODS.add(Material.COOKED_FISH);
		FOODS.add(Material.CARROT);
		FOODS.add(Material.CARROT_ITEM);
		FOODS.add(Material.APPLE);
		FOODS.add(Material.POTATO);
		FOODS.add(Material.POISONOUS_POTATO);
		FOODS.add(Material.BAKED_POTATO);
		FOODS.add(Material.GOLDEN_APPLE);
		FOODS.add(Material.GOLDEN_CARROT);
		FOODS.add(Material.BREAD);
		FOODS.add(Material.MELON);
		FOODS.add(Material.MUSHROOM_SOUP);
		FOODS.add(Material.ROTTEN_FLESH);
		FOODS.add(Material.COOKIE);
		FOODS.add(Material.PUMPKIN_PIE);
		FOODS.add(Material.SPIDER_EYE);
		FOODS.add(Material.MILK_BUCKET);
		FOODS.add(Material.POTION);
	}
}
