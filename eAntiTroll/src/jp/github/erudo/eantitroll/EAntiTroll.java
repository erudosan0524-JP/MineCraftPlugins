package jp.github.erudo.eantitroll;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import jp.github.erudo.eantitroll.events.DispenserListener;
import jp.github.erudo.eantitroll.events.JoinQuitListener;
import jp.github.erudo.eantitroll.events.LavaListener;
import jp.github.erudo.eantitroll.events.MoveListener;
import jp.github.erudo.eantitroll.events.TNTListener;
import jp.github.erudo.eantitroll.events.VehicleListener;
import jp.github.erudo.eantitroll.utils.Config;

public class EAntiTroll extends JavaPlugin {

	Player player = null;
	World world = null;
	Block block = null;

	String name = null;
	boolean lava1 = false;

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

		getCommand("eantitroll").setExecutor(new CommandManager(this));

		//プラグインの検知
//		try {
//			for(Plugin plgs : getServer().getPluginManager().getPlugins()) {
//
//				if(plgs instanceof MultiverseCore) {
//					MVFlag = true;
//				}
//			}
//		}catch(NoClassDefFoundError e) {
//			this.getLogger().info("Multivers-Coreが見つかりませんでした");
//		}
	}




}
