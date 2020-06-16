package com.github.jp.erudo.eanticheat.checks.movements;

import org.bukkit.potion.PotionEffectType;

import com.github.jp.erudo.eanticheat.checks.CheckResult;
import com.github.jp.erudo.eanticheat.checks.CheckType;
import com.github.jp.erudo.eanticheat.checks.Level;
import com.github.jp.erudo.eanticheat.utils.Distance;
import com.github.jp.erudo.eanticheat.utils.Settings;
import com.github.jp.erudo.eanticheat.utils.User;

public class NoSlowDown {

	private static final CheckResult PASS = new CheckResult(Level.PASSED, null, CheckType.NOSLOW);

	public static void registerMove(Distance d, User u) {
		if (u.getPlayer().hasPotionEffect(PotionEffectType.SPEED))
			return;

		u.setCurrentFoodLevel(u.getPlayer().getFoodLevel());

		System.out.println(u.getCurrentFoodLevel());
		double xzDist = (d.getxDiff() > d.getzDiff() ? d.getxDiff() : d.getzDiff());

		if (xzDist > Settings.MAX_XZ_EATING_SPEED && u.getFoodStart() != null
				&& System.currentTimeMillis() - u.getFoodStart() > 1200) {
			u.addInvalidFoodEatableCount();
		}

	}

	public static CheckResult runCheck(Distance d, User u) {
		double xzDist = (d.getxDiff() > d.getzDiff() ? d.getxDiff() : d.getzDiff());

		//ブロックに関してのNoSlowDownの検知
		if (u.getPlayer().isBlocking() && xzDist > Settings.MAX_XZ_BLOCKING_SPEED) {
			return new CheckResult(Level.DEFINITELY, "tried to move too fast while blocking", CheckType.NOSLOW);

		}

		return PASS;
	}

}
