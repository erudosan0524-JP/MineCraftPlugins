package com.github.erudo0524.eoni2.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.erudo0524.eoni2.Main;

public class Config {

	private final Main plg;
	private FileConfiguration config = null;

	//ここからConfigの内容用の変数
	private int defaultTime;
	private boolean ModeHue;
	private int supplyChestInterval;

	private String confOniPos;
	private String confTpPos;
	private String confDeleteBlockPos;
	private List<String> confSupplyChestPos;

	private String defaultWorld;

	public Config(Main plg) {
		this.plg = plg;
		load();
	}

	private void load() {
		plg.saveDefaultConfig();
		if (config != null) { // configが非null == リロードで呼び出された
			plg.reloadConfig();
		}
		config = plg.getConfig();

		defaultTime = config.getInt("defaultTime");
		supplyChestInterval = config.getInt("supplyChestInterval");
		ModeHue = config.getBoolean("isModeHue");
		confOniPos = config.getString("OniPosition");
		confTpPos = config.getString("TpPosition");
		confDeleteBlockPos = config.getString("DeleteBlockPosition");
		confSupplyChestPos = config.getStringList("supplyChestPositions");
		defaultWorld = config.getString("defaultWorld");

	}

	public String getDefaultWorld() {
		return defaultWorld;
	}

	public int[] getConfOniPos() {
		String[] str = confOniPos.split(" ", 0);
		for (String arg : str) {
			if (arg.isEmpty() || str.equals(null)) {
				plg.getServer().getLogger().info("strはnullでした");
				return null;
			}
		}
		int[] num = new int[3];
		for (int i = 0; i < 3; i++) {
			//0,1,2
			num[i] = Integer.parseInt(str[i]);
		}
		return num;

	}

	public int[] getConfTpPos() {
		String[] str = confTpPos.split(" ", 0);
		for (String arg : str) {
			if (arg.isEmpty() || str.equals(null)) {
				plg.getServer().getLogger().info("strはnullでした");
				return null;
			}
		}
		int[] num = new int[3];
		for (int i = 0; i < 3; i++) {
			//0,1,2
			num[i] = Integer.parseInt(str[i]);
		}
		return num;
	}

	public int[] getConfDeleteBlockPos() {
		String[] str = confDeleteBlockPos.split(" ", 0);
		for (String arg : str) {
			if (arg.isEmpty() || str.equals(null)) {
				plg.getServer().getLogger().info("strはnullでした");
				return null;
			}
		}
		int[] num = new int[3];
		for (int i = 0; i < 3; i++) {
			//0,1,2
			num[i] = Integer.parseInt(str[i]);
		}
		return num;
	}

	public List<int[]> getConfSupplyChestPos() {
		List<int[]> returns = new ArrayList<int[]>();
		for (String s : confSupplyChestPos) {
			String[] args = s.split(" ", 0);
			for (String arg : args) {
				if (arg.isEmpty() || args.equals(null)) {
					plg.getServer().getLogger().info("argsはnullでした");
					return null;
				}
			}
			int[] num = new int[3];
			for (int i = 0; i < 3; i++) {
				//0,1,2
				try {
					num[i] = Integer.parseInt(args[i]);
				} catch (NumberFormatException e) {
					num[i] = 0;
				}
			}
			returns.add(num);
		}

		return returns;
	}

	public int getDefaultTime() {
		return defaultTime;
	}

	public boolean isModeHue() {
		return ModeHue;
	}

	public int getSupplyChestInterval() {
		return supplyChestInterval;
	}

	public void setSupplyChestInterval(int supplyChestInterval) {
		this.supplyChestInterval = supplyChestInterval;
	}

}
