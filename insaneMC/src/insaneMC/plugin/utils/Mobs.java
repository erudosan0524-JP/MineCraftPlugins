package insaneMC.plugin.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;

public class Mobs {

	public static  List<Entity> creepers = new ArrayList<>();
	public static String creeperName = ChatColor.GREEN + "【" + ChatColor.RED + "強" + ChatColor.GREEN + "】クリーパー";

	public static void initList() {
		creepers.removeAll(creepers);
	}
}
