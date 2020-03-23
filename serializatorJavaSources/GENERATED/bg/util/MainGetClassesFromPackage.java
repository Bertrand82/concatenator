package bg.util;

import java.util.List;

public class MainGetClassesFromPackage {

    public static void main(final String[] args) {
        traceClasses("bg");
        traceClasses("fr.xebia");
    }

    private static void traceClasses(final String packageName) {
        System.out.println("Package name " + packageName);
        final List<Class> listClasses = UtilPackage.getClassesFromPackage(packageName);
        for (final Class clazz : listClasses) {
            System.out.println(clazz.getName());
        }
        System.out.println("Package name " + packageName + "  list : " + listClasses.size());
    }

}



/**
 *
 */
