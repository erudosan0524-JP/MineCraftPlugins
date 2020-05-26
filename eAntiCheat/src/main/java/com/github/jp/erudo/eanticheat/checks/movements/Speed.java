package com.github.jp.erudo.eanticheat.checks.movements;

import org.bukkit.GameMode;
import org.bukkit.potion.PotionEffectType;

import com.github.jp.erudo.eanticheat.checks.CheckResult;
import com.github.jp.erudo.eanticheat.checks.CheckType;
import com.github.jp.erudo.eanticheat.checks.Level;
import com.github.jp.erudo.eanticheat.utils.Distance;
import com.github.jp.erudo.eanticheat.utils.Settings;
import com.github.jp.erudo.eanticheat.utils.User;

public class Speed {

	private final static CheckResult PASS = new CheckResult(Level.PASSED, null, CheckType.SPEED);

	public static CheckResult runCheck(Distance d, User u) {

		if (u.getPlayer().getGameMode() == GameMode.CREATIVE || u.getPlayer().getGameMode() == GameMode.SPECTATOR) {
			return PASS;
		}

		if (u.getPlayer().hasPotionEffect(PotionEffectType.SPEED)
				|| u.getPlayer().hasPotionEffect(PotionEffectType.JUMP)) {
			return PASS;
		}

		if(u.getPlayer().getAllowFlight()) {
			return PASS;
		}


		//x,zのうち変化が大きいほうが，MAX_XZ_SPEEDより大きかったら
		//すなわち，XZの移動する幅（スピード）が普段より大きかったら
		Double xzSpeed = (d.getxDiff() > d.getzDiff() ? d.getxDiff() : d.getzDiff());
		if (xzSpeed > Settings.MAX_XZ_SPEED) {

			return new CheckResult(
					Level.DEFINITELY,
					"speed=(" + xzSpeed.toString() + ") max=(" + Settings.MAX_XZ_SPEED + ")",
					CheckType.SPEED);

		}

		return PASS;
	}

}
