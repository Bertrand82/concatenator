package serializatorJavaSources;

import java.io.File;

public class MainTest {

	public MainTest() {
	}

	public static void main(String[] args) {
		File dir = new File("GENERATED");
		String packageName = "a.b.cc.ddd";
		System.out.println("lengyh "+packageName.split("\\.").length);
		for (String p : packageName.split("\\.")) {
			dir = new File(dir, p);
		}
		System.err.println(dir.getAbsolutePath());
	}

}

