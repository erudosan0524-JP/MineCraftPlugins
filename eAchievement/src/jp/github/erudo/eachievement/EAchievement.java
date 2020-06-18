package jp.github.erudo.eachievement;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import jp.github.erudo.eachievement.events.OnPlayerJoin;
import jp.github.erudo.eachievement.utils.FileManager;

public class EAchievement extends JavaPlugin {

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが起動しました");
		//Achieve.txtの作成
		FileManager fileManager = new FileManager("eAchievement/achievements.txt",this);
		try {
			fileManager.createFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new OnPlayerJoin(this);
	}



}
