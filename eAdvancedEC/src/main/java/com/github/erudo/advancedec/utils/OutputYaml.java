package com.github.erudo.advancedec.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import com.github.erudo.advancedec.Main;

public class OutputYaml extends YAMLManager {

	public OutputYaml(Main main) {
		super(main, "output.yml");
	}

	public void setContent(UUID id,String s) {
		config.set(id.toString(), s);
	}

	public String getContent(String path) {
		return config.getString(path);
	}

	public void write(String s) throws IOException {
		FileWriter fw = new FileWriter("");
		PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

		pw.println(s);

		pw.close();

	}

}
