package bg.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bgui
 *
 */
public class UtilPackage {

    /**
     * Cette methode return toutes les class presente dans un package
     *
     * @param pClazz1
     * @return
     */
    private static List<Class> getSubClasses(final String packageName) {
        final List<Class> listClasses = new ArrayList<>();
        final String javaClassPaths[] = System.getProperty("java.class.path").split(";");
        for (final String s : javaClassPaths) {
            if (s.endsWith(".jar")) {
                //processJarFile(s, clazz1);
            } else {
                final File dirClasses = new File(s);
                final File dirPackage = new File(dirClasses, packageToPath(packageName));
                if (dirPackage.exists()) {
                    final String[] names = dirPackage.list();
                    for (final String name : names) {

                        if ((name.indexOf("$") < 0) && (name.indexOf(".class") > 0)) {
                            final String sName = name.substring(0, name.indexOf("."));
                            final String className = packageName + "." + sName;

                            try {
                                final Class clazz = UtilPackage.class.getClassLoader().loadClass(className);
                                listClasses.add(clazz);
                            } catch (final ClassNotFoundException e) {
                                System.err.println("ClassNotFound : " + e.getMessage());
                            }
                        }
                    }
                }
            }
        }
        return listClasses;
    }

    private static String packageToPath(final String packageName) {
        return packageName.replaceAll("\\.", "/");
    }

    private static File getClassPathInDirClasses(final String packageName) {
        final String javaClassPaths[] = System.getProperty("java.class.path").split(";");
        for (final String s : javaClassPaths) {
            if (s.endsWith(".jar")) {

            } else {
                final File dirClasses = new File(s);
                final File dirPackage = new File(dirClasses, packageToPath(packageName));
                if (dirPackage.exists()) {
                    return dirClasses;
                }
            }
        }
        return null;
    }

    /**
     *
     *
     * @param clazz
     *            la classe permettant de connaître le directory
     * @param packageName
     * @return
     */
    public static List<Class> getClassesFromPackage(final String packageName) {

        final File dirPath = getClassPathInDirClasses(packageName);
        if (dirPath == null) {
            return UtilJar.getClassesFromPackage(packageName);
        } else {
            final File dirPackage = new File(dirPath, packageToPath(packageName));
            return getClassesFromDir_(dirPackage, packageName);
        }

    }

    /**
     *
     * @param pDirPackage
     * @return
     */
    private static List<Class> getClassesFromDir_(final File dirPackage, final String packageName) {
        final List<Class> listClasses = new ArrayList<>();
        if (dirPackage.exists()) {
            final String[] names = dirPackage.list();
            for (final String name : names) {

                if ((name.indexOf("$") < 0) && (name.indexOf(".class") > 0)) {
                    final String sName = name.substring(0, name.indexOf("."));
                    final String className = packageName + "." + sName;
                    try {
                        final Class clazz = UtilPackage.class.getClassLoader().loadClass(className);
                        listClasses.add(clazz);
                    } catch (final ClassNotFoundException e) {
                        System.out.println("ClassNotFound :" + e.getMessage());
                    }
                }
            }
        }
        for (final File child : dirPackage.listFiles()) {
            if (child.isDirectory()) {
                final List<Class> l = getClassesFromDir_(child, packageName + "." + child.getName());
                listClasses.addAll(l);
            }
        }
        return listClasses;
    }

    public static void main(final String[] s) {

        final List<Class> list = getClassesFromPackage("bg");
        System.out.println("list result " + list.size());
        for (final Class c : list) {
            System.out.println("Result : " + c.getName());
        }

    }

}



