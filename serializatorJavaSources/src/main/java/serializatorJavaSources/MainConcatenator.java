package serializatorJavaSources;

import java.io.File;
import java.nio.file.Files;

/**
 * @author c82bgui
 * Cette class concatene des ficchiers sources en une seule String facile a colé dans un mail. Utile lorsque les zip sont interdits 
 * Pour deconcatener, il faut utiliser  utilisé le deconcatenator!
 */
public class MainConcatenator {

    static String fileNames = "";

    static int i = 0;

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final File dir = new File("src/main/java");
        System.out.println("dir " + dir.exists() + "  " + dir.getAbsolutePath());
        final String s = getConcatenate(dir);
        System.out.println(s);
        System.out.println(fileNames);

    }

    /**
     * @param pDir
     * @return
     */
    private static String getConcatenate(final File pDir) {
        String s = "\n wwwwwwwwwwwwww " + pDir.getName() + " wwwwwwwwwww\n";
        for (final File child : pDir.listFiles()) {
            if (child.isDirectory()) {
                s += getConcatenate(child);
            } else {
                s += "\n\n xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx  Concatenator : " + child.getName() + "xxxx\n\n";
                s += getFile(child);
                fileNames += i++ + " " + pDir.getName() + "  " + child.getName() + "\n";
            }
        }
        return s;
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





