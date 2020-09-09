package com.github.jp.erudo.eantitroll;

import java.util.Objects;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.jp.erudo.eantitroll.events.DispenserListener;
import com.github.jp.erudo.eantitroll.events.JoinQuitListener;
import com.github.jp.erudo.eantitroll.events.LavaListener;
import com.github.jp.erudo.eantitroll.events.MoveListener;
import com.github.jp.erudo.eantitroll.events.TNTListener;
import com.github.jp.erudo.eantitroll.events.VehicleListener;
import com.github.jp.erudo.eantitroll.utils.Config;

public class Main extends JavaPlugin {

	public static boolean MVFlag = false;

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");

	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが読み込まれました");
		new DispenserListener(this);
		new JoinQuitListener(this);
		new LavaListener(this);
		new MoveListener(this);
		new TNTListener(this);
		new VehicleListener(this);

		new Config(this);

		getCommand("eat").setExecutor(new CommandManager(this));


		//Multiverse-core check
		Plugin temp = getServer().getPluginManager().getPlugin("Multiverse-Core");
		if(Objects.isNull(temp)) {
			MVFlag = false;
		} else {
			MVFlag = true;
		}
	}




}
