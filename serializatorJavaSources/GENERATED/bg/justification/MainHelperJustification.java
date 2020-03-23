package bg.justification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import fr.atom.sante.prestationnature.gestionfactures.domain.facture.Justification;

/**
 * @author c82bgui
 *
 */
public class MainHelperJustification {

    final static List<Class> listClassesEnum = new ArrayList();

    public static void main(final String[] s) {
        final Class clazzJustification = Justification.class;
        System.out.println("\n\npublic class ElementJustificationKeys {\n\n");
        for (final Field field : clazzJustification.getDeclaredFields()) {

            if (field.getName().equals("serialVersionUID")) {
            } else {
                String comment = "Type :" + field.getType().getSimpleName();
                if (field.getType().isEnum()) {

                    comment += " isEnum :" + enumValue(field.getType());
                }
                System.out.println("");
                System.out.println("/* " + comment + " */");
                System.out.println(
                        "    public static final  String KEY_" + field.getName() + " = \"" + field.getName() + "\";");
            }
        }

        displayEnums(listClassesEnum);
        System.out.println("}");
    }

    /**
     * @param pType
     * @return
     */
    private static String enumValue(final Class<?> clazz) {
        String s = "";
        if (!listClassesEnum.contains(clazz)) {
            listClassesEnum.add(clazz);
        }

        for (final Field f : clazz.getDeclaredFields()) {
            if (f.getName().equals("ENUM$VALUES")) {

            } else {
                s += f.getName() + "; ";
            }
        }
        return s;

    }

    /**
     * @param pListclassesenum
     */
    private static void displayEnums(final List<Class> pListclassesenum) {
        for (final Class clazz : pListclassesenum) {
            displayClassEnum(clazz);
        }

    }

    /**
     * @param pClazz
     */
    private static void displayClassEnum(final Class clazz) {
        System.out.println("\n\nstatic class " + clazz.getSimpleName() + "{");
        for (final Field f : clazz.getDeclaredFields()) {
            if (f.getName().equals("ENUM$VALUES")) {

            } else {
                final String s = "public static final String KEY_" + f.getName() + " = \"" + f.getName() + "\"; ";
                System.out.println(s);
            }
        }
        System.out.println("}");
    }
}



/**
 *
 */
