package com.github.jp.erudo.ebowspleef2.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.github.jp.erudo.ebowspleef2.Main;
import com.github.jp.erudo.ebowspleef2.enums.Teams;


public class PlayersSetting {

	private static Location bluePos;
	private static Location redPos;
	private static Location lobbyPos;

	public static void addPlayerToTeam(Teams teams, Player p) {
		if (teams == Teams.RED) {
			Main.getRed().addEntry(p.getName());
		} else if (teams == Teams.BLUE) {
			Main.getBlue().addEntry(p.getName());
		}
	}

	public static Location getRedPos() {
		return PlayersSetting.redPos;
	}

	public static void setRedPos(Location redPos) {
		PlayersSetting.redPos = redPos;
	}

	public static Location getBluePos() {
		return PlayersSetting.bluePos;
	}

	public static void setBluePos(Location bluePos) {
		PlayersSetting.bluePos = bluePos;
	}

	public static Location getLobbyPos() {
		return lobbyPos;
	}

	public static void setLobbyPos(Location lobbyPos) {
		PlayersSetting.lobbyPos = lobbyPos;
	}

}
