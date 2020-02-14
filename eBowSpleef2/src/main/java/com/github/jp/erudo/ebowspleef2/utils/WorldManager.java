package com.github.jp.erudo.ebowspleef2.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.world.DataException;

@SuppressWarnings("deprecation")
public class WorldManager {

	//worldeditで保存したschematicファイルを読み込んでペーストするメソッド
	public void loadSchematic(Player player, String fileName) {
		Location location = player.getLocation();
		WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
		File schematic = new File(worldEditPlugin.getDataFolder() + File.separator + "/schematics/" + fileName + ".schematic");
		//worldeditを実行するためのセッションを作成する
		EditSession settion = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(location.getWorld()), 200000);

		try {
			//schematic形式のファイルをロードしてクリップボードにコピー
			CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
			//指定座標をベクトルで指定してペースト
			clipboard.paste(settion, new Vector(location.getX(),location.getY(),location.getZ()), false);
		} catch(MaxChangedBlocksException | DataException | IndexOutOfBoundsException | IOException e) {
			e.printStackTrace();
		}

	}

	public void loadSchematic(Location loc, String fileName) {
		WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
		File schematic = new File(worldEditPlugin.getDataFolder() + File.separator + "/schematics/" + fileName + ".schematic");
		EditSession settion = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(loc.getWorld()), 200000);

		try {
			CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(schematic).load(schematic);
			clipboard.paste(settion, new Vector(loc.getX(),loc.getY(),loc.getZ()), false);
		} catch(MaxChangedBlocksException | DataException | IndexOutOfBoundsException | IOException e) {
			e.printStackTrace();
		}
	}
}
