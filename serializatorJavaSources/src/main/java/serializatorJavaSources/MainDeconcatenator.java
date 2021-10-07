package serializatorJavaSources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MainDeconcatenator {

	public MainDeconcatenator() {

	}

	private static File dirOut = new File("GENERATED");

	public static void main(String[] args) throws Exception {
		dirOut.mkdirs();
		File fileSrc = new File("serialSources.txt");
		desiaralize(fileSrc);
	}

	public static void desiaralize(File fileSrc) throws Exception {
		System.out.println("Start " + fileSrc.getName() + "  exists : " + fileSrc.exists());
		FileReader in = new FileReader(fileSrc);
		BufferedReader br = new BufferedReader(in);
		desiaralize(br, dirOut);
	}

	public static void desiaralize(BufferedReader br, File dirOut) throws Exception {
		String line = null;
		String clazzText = "";
		String packageName = "";
		String className = null;
		while ((line = br.readLine()) != null) {

			if (line.trim().startsWith("package ")) {
				processClazz(packageName, className, clazzText, dirOut);
				packageName = getPaxkageNameFromLine(line);
				clazzText = "";
			} else if (line.trim().startsWith("public class")) {
				className = getClassNameFromLine(line);
			}

			if (line.trim().startsWith("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx")) {
				// Inutile
			} else {
				clazzText += line + "\n";
			}
		}
		processClazz(packageName, className, clazzText, dirOut);
	}

	private static void processClazz(String packageName, String className, String clazzText, File dirOut) {
		if (className == null) {
			return;
		}
		Clazz clazz = new Clazz(packageName, className, clazzText);
		clazz.writeTo(dirOut);
	}

	private static String getPaxkageNameFromLine(String line) {
		String s = line.trim().replace("package", " ").replace(";", "").trim();
		return s;
	}

	private static String getClassNameFromLine(String line) {
		String s = line.trim().replace("public", " ").replace("{", " ").replace("class", " ").trim();
		return s;
	}

}




