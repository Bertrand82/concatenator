package bm1.generation.code.old.poubelle;

/**
 * @author c82bgui
 *
 */
public class MainGenerateAbstrctMapper {

    String packageSourcePath = "D:\\platformsg2_R_64\\workspace\\bm1atom-tarif\\bm1atom-tarif-ws\\target\\generated-sources\\cxf";

    /**
     * @param args
     */
    public static void main(final String[] args) throws Exception {

        System.err.println("start");
        Thread.sleep(100);
        final Class clazz1 = getClassByName(
                "fr.atom.sante.relationsassure.contratassurance.domain.contratassurance.SoinsPlanifies");
        final Class clazz2 = getClassByName(
                "fr.msa.atom.sante.prestationsennature.facturepn.objets.individusanteatom.v1.SoinsPlanifies");
        System.err.println("Start ---------------- " + clazz1);
        System.err.println("Start ---------------- " + clazz2);
        //        final GeneratorMapperAbstractClass gmpa = new GeneratorMapperAbstractClass(clazz1, clazz2);
    }

    private static Class getClassByName(final String s) throws ClassNotFoundException {
        return MainGenerateAbstrctMapper.class.getClassLoader().loadClass(s);
    }

}



/**
 *
 */
