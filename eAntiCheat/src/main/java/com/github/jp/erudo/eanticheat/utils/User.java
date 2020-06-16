package com.github.jp.erudo.eanticheat.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class User {

	private Player player;
	private Long foodStart;
	private Location foodStartLoc;
	private int invalidFoodEatableCount = 0;
	private int currentFoodLevel = 0;

	public User(Player p) {
		this.player = p;
	}

	public Player getPlayer() {
		return this.player;
	}

	public Long getFoodStart() {
		return foodStart;
	}

	public void setFoodStart() {
		this.foodStart = System.currentTimeMillis();
		this.foodStartLoc = player.getLocation();
	}

	public boolean isFoodStarted() {
		return this.foodStart != null;
	}

	public int getInvalidFoodEatableCount() {
		return invalidFoodEatableCount;
	}

	public void addInvalidFoodEatableCount() {
		this.invalidFoodEatableCount++;
	}

	public Location getFoodStartLoc() {
		return foodStartLoc;
	}

	public void resetInvalidFoodEatableCount() {
		this.invalidFoodEatableCount = 0;

	}

	public int getCurrentFoodLevel() {
		return currentFoodLevel;
	}

	public void setCurrentFoodLevel(int currentFoodLevel) {
		this.currentFoodLevel = currentFoodLevel;
	}



}
