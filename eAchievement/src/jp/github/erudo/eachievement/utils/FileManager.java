package jp.github.erudo.eachievement.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.bukkit.plugin.Plugin;

public class FileManager {

	private File file;
	private PrintWriter pw;
	private Plugin plg;

	public FileManager(String file, Plugin plg) {
		this.plg = plg;
		this.file = new File(file);
	}

	public void createFile() throws IOException {
		pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		if(file.createNewFile()) {
			pw.println("#####Achieves#####");
			pw.close();
			plg.getLogger().info("ファイル作成成功");
		}else {
			plg.getLogger().info("ファイル作成失敗");
		}
	}


}
