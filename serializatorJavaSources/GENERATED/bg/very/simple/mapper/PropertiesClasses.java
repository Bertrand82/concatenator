package bg.very.simple.mapper;

import java.util.Properties;

/**
 * @author c82bgui
 *
 */
public class PropertiesClasses {

    public static Properties pClasses = new Properties();
    static {
        pClasses.setProperty("fr.msa.atom.sante.prestationsennature.facturepn.objets.individusanteatom.v1.Assureur",
                "fr.atom.referentiel.assurance.domain.assureur.Assureur");
        pClasses.setProperty(
                "fr.msa.atom.sante.prestationsennature.facturepn.objets.individusanteatom.v1.SoinsPlanifies",
                "fr.atom.sante.relationsassure.contratassurance.domain.contratassurance.SoinsPlanifies");
    }

    static Class getClassesPair(final String className) {
        try {
            final String name = pClasses.getProperty(className);
            if (name == null) {
                return null;
            }
            return PropertiesClasses.class.getClassLoader().loadClass(name);
        } catch (final ClassNotFoundException e) {
            return null;
        }

    }
}

 wwwwwwwwwwwwww bm1 wwwwwwwwwww

 wwwwwwwwwwwwww generation wwwwwwwwwww

 wwwwwwwwwwwwww code wwwwwwwwwww

 wwwwwwwwwwwwww old wwwwwwwwwww

 wwwwwwwwwwwwww poubelle wwwwwwwwwww



/**
 *
 */
