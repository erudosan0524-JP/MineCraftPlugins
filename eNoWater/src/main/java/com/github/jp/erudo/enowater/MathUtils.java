package com.github.jp.erudo.enowater;

public class MathUtils {

	public static double eFloor(double num, int base) {
		return Math.floor(num * base) / base;
	}

	public static double eRound(double num, int base) {
		return Math.round(num * base) / base;
	}

	public static double eCeil(double num, int base) {
		return Math.ceil(num * base) / base;
	}
}
