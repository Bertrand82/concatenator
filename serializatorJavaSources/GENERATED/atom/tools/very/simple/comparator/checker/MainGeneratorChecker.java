package atom.tools.very.simple.comparator.checker;

public class MainGeneratorChecker {

    /**
     * Generation du code permettant de verifier qu'aucun String n'a �t� oubli�.
     * La classe g�n�r�e est incorpor�e dans le projet bm1atom-tarif-ws
     *
     * @param s
     * @throws Exception
     */
    public static void main(final String[] s) throws Exception {
        final Class clazzIn = fr.msa.atom.sante.prestationsennature.facturepn.objets.factureatom.v1.InfoFactureCalculee.class;
        final Class clazzOut = fr.msa.atom.sante.prestationsennature.facturepn.objets.v1.InfoFactureCalculee.class;
        System.out.println("MainComparator start ");
        final GeneratorCheckerStringForgottenClass generator = new GeneratorCheckerStringForgottenClass(clazzOut,
                clazzIn);
    }
}

 wwwwwwwwwwwwww bg wwwwwwwwwww



