package bg.mapper.helper;

import java.util.List;

import bg.util.UtilPackage;

/**
 * @author c82bgui
 *
 */
public class MainListClassFromPackage {

    public static void main(final String[] s) {
        final String packageName = "atom.msa.fr.sante.prestationsennature.facturepn.enumcustommappers.v1";
        final List<Class> listClasses = UtilPackage.getClassesFromPackage(packageName);
        System.out.println("\n\n");
        for (final Class clazz : listClasses) {
            System.out.println("import " + clazz.getName() + " ;");
        }
        System.out.println("\n\n");
        int i = 0;
        for (final Class clazz : listClasses) {
            final String name = clazz.getSimpleName();
            System.out.println("" + name + "  " + name.toLowerCase() + "  = new " + name + "() " + ";");

        }
        for (final Class clazz : listClasses) {
            final String name = clazz.getSimpleName();
            System.out.println(name.toLowerCase() + ".map(\"Azerty" + i++ + "\");");
        }
    }
}



