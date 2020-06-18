package com.github.jp.erudo.mathematics;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.jp.erudo.mathematics.listener.BowShotListener;

public class Main extends JavaPlugin {

	//テスト用
	public static List<Block> BlockList = new ArrayList<Block>();

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました");

		new BowShotListener(this);
	}

}
