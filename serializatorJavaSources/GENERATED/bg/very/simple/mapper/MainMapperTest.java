package bg.very.simple.mapper;

/**
 * @author c82bgui
 *
 */

import java.io.File;

/**
 * @author c82bgui
 *
 */
public class MainMapperTest {

    /**
     * @author bgui
     *
     *
     *
     *         private String packageSourcePath = "D:\\";
     *
     *         /**
     * @param args
     */
    public static void main(final String[] args) throws Exception {
        System.err.println("MainMapperTest Start");
        final File dirOutput = new File("GENERATED3");
        final String package1 = "fr.msa.atom.sante.prestationsennature.facturepn";
        final String package2 = "fr.msa.si.sante.prestationsennature.facturepn";
        final GeneratorVerySimpleMapper gmpa = new GeneratorVerySimpleMapper(dirOutput, package1, package2, "bg.test");

    }

}



