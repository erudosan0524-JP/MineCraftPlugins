package com.github.jp.erudo.eanticheat.checks.player;

import com.github.jp.erudo.eanticheat.checks.CheckResult;
import com.github.jp.erudo.eanticheat.checks.CheckType;
import com.github.jp.erudo.eanticheat.checks.Level;
import com.github.jp.erudo.eanticheat.utils.Settings;
import com.github.jp.erudo.eanticheat.utils.User;

public class FastUse {

	private static final CheckResult PASS = new CheckResult(Level.PASSED, null, CheckType.FASTUSE);

	public static CheckResult runBowCheck(User u) {

		long now = System.currentTimeMillis();

		if(u.getBowStart() != null && now - u.getBowStart() < Settings.BOW_MIN) {

			return new CheckResult(Level.DEFINITELY, "tried to shoot too fast; time=(" + (now - u.getBowStart()) + "), min=(" + Settings.BOW_MIN + ")", CheckType.FASTUSE);
		}

		return PASS;
	}


	public static CheckResult runFoodCheck(User u) {

		long now = System.currentTimeMillis();

		if(u.getFoodStart() != null && now - u.getFoodStart() < Settings.FOOD_MIN) {

			return new CheckResult(Level.DEFINITELY, "tried to eat too fast; time=(" + (now - u.getFoodStart()) + "), min=(" + Settings.FOOD_MIN + ")", CheckType.FASTUSE);
		}

		return PASS;
	}
}
