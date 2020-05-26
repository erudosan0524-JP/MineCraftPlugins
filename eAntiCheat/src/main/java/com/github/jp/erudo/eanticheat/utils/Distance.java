package com.github.jp.erudo.eanticheat.utils;

import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;

//移動した距離の差異を図るクラス
public class Distance {

	private Location from, to;
	private Double xDiff,yDiff,zDiff;

	public Distance(PlayerMoveEvent e) {
		this.from = e.getFrom();
		this.to = e.getTo();
		/*
		 * fromのx座標がtoのx座標よりも大きい時，fromのxを返し，
		 * 小さいときはtoX - fromXを返す
		 */
		this.xDiff = (from.getX() > to.getX() ? from.getX() : to.getX()) - (from.getX() < to.getX() ? from.getX() : to.getX());
		this.yDiff = (from.getY() > to.getY() ? from.getY() : to.getY()) - (from.getY() < to.getY() ? from.getY() : to.getY());
		this.zDiff = (from.getZ() > to.getZ() ? from.getZ() : to.getZ()) - (from.getZ() < to.getZ() ? from.getZ() : to.getZ());

	}

	public Location getFrom() {
		return from;
	}

	public Location getTo() {
		return to;
	}

	public Double getxDiff() {
		return xDiff;
	}

	public Double getyDiff() {
		return yDiff;
	}

	public Double getzDiff() {
		return zDiff;
	}

}
