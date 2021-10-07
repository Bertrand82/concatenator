package serializatorJavaSources;

import java.io.File;
import java.nio.file.Files;

/**
 * @author c82bgui Cette class concatene des ficchiers sources en une seule
 *         String facile a colé dans un mail. Utile lorsque les zip sont
 *         interdits Pour deconcatener, il faut utiliser utilisé le
 *         deconcatenator!
 */
public class MainConcatenator {

	static String fileNames = "";

	static int i = 0;

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		File dirProjet;
		if (args.length > 0) {
			dirProjet = new File(args[0]);
		} else {
			dirProjet = new File(".");
		}
		final File dir = new File(dirProjet, ".");
		System.out.println("dir " + dir.exists() + "  " + dir.getAbsolutePath());
		final String s = getConcatenate(dir);
		System.out.println(s);
		System.out.println(fileNames);
	}

	/**
	 * @param pDir
	 * @return
	 */
	public static String getConcatenate(final File pDir) {
		String s = "\n wwwwwwwwwwwwww " + pDir.getName() + " wwwwwwwwwww\n";
		for (final File child : pDir.listFiles()) {
			if (child.isDirectory()) {
				if (isDirEligible(child)) {
					s += getConcatenate(child);
				}
			} else if (isFileEligible(child)) {
				s += "\n\n xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  Concatenator : " + child.getName()
						+ "xxxx\n\n";
				s += getFile(child);
				fileNames += i++ + " " + pDir.getName() + "  " + child.getName() + "\n";
			}
		}
		return s;
	}

	private static boolean isFileEligible(File file) {
		if (file.getName().startsWith(".")) {
			return false;
		}
		if (file.getName().endsWith(".class")) {
			return false;
		}
		if (file.getName().endsWith(".jar")) {
			return false;
		}
		if (file.getName().endsWith(".zip")) {
			return false;
		}
		return true;
	}

	private static boolean isDirEligible(File dir) {
		String nameDir = dir.getName();
		if ("target".equals(nameDir)) {
			return false;
		}
		if (nameDir.startsWith(".")) {
			return false;
		}
		return true;
	}

	/**
	 * @param pChild
	 * @return
	 */
	private static String getFile(final File file) {
		try {
			final byte[] fileBytes = Files.readAllBytes(file.toPath());
			return new String(fileBytes);
		} catch (final Exception e) {
			return "Exception " + e.getMessage() + " : " + file.getName();
		}
	}

}



