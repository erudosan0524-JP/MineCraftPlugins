package com.github.erudo.advancedec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_14_R1.inventory.CraftInventoryCustom;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.github.erudo.advancedec.listener.OnChestClose;
import com.github.erudo.advancedec.listener.OnChestOpen;

public class Main extends JavaPlugin {

	public static final String CHESTNAME = "AdvancedEnderChest";

	private Config config;

	private HashMap<UUID, String> inventories = new HashMap<UUID, String>();

	@Override
	public void onDisable() {
		getLogger().info("プラグインが停止しました");
	}

	@Override
	public void onEnable() {
		getLogger().info("プラグインが読み込まれました");

		////////////////////////
		///		Listener 	///
		///////////////////////
		new OnChestOpen(this);
		new OnChestClose(this);

		////////////////////////
		///		Config 		///
		///////////////////////
		config = new Config(this);
	}

	//セーブ関連
	private String InventorytoBase64(Inventory inventory) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataoutput = new BukkitObjectOutputStream(outputStream);

			dataoutput.writeInt(inventory.getSize());

			for (int i = 0; i < inventory.getSize(); i++) {
				dataoutput.writeObject(inventory.getItem(i));
			}

			dataoutput.close();
			return Base64Coder.encodeLines(outputStream.toByteArray());
		} catch (Exception e) {
			throw new IllegalStateException("Could not convert inventory to base64.", e);
		}
	}

	private Inventory InventoryfromBase64(String data) throws IOException {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
			BukkitObjectInputStream datainput = new BukkitObjectInputStream(inputStream);

			CraftInventoryCustom inventory = new CraftInventoryCustom(null, datainput.readInt(),CHESTNAME);

			for (int i = 0; i < inventory.getSize(); i++) {
				inventory.setItem(i, (ItemStack) datainput.readObject());
			}

			datainput.close();
			return inventory;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IOException("Could not decode inventory.", e);
		}

	}

	public void saveInventory(Player p, Inventory inventory) {
		String data = this.InventorytoBase64(inventory);

		if (this.inventories.containsKey (p.getUniqueId())) {
			this.inventories.replace(p.getUniqueId(),data);
			System.out.println("replace");
		} else {
			this.inventories.put(p.getUniqueId(), data);
			System.out.println("put");
		}
	}

	public Inventory getSavedInventory(Player p) throws IOException {
		System.out.println(inventories);
		if (this.inventories.containsKey(p.getUniqueId())) {
			return this.InventoryfromBase64(inventories.get(p.getUniqueId()));
		} else {
			return Bukkit.getServer().createInventory(null, 9 * this.getChestRow(),CHESTNAME);
		}
	}

	public void saveInventory() {

	}

	public int getChestRow() {
		return config.getChestRow();
	}

}
