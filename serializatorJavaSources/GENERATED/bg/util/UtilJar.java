package bg.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author c82bgui
 *         Obtenir toutes les classes d'un package
 */
public class UtilJar {

    public static void main(final String[] a) throws Exception {
        final List<Class> listClasses = getClassesFromPackage("com.squareup.javapoet");
    }

    public static List<Class> getClassesFromPackage(final String packageName) {

        final List<Class> listClasses = new ArrayList<>();

        System.err.println("UtilJar getSubClasses " + packageName);

        final String javaClassPaths[] = System.getProperty("java.class.path").split(";");
        for (final String s : javaClassPaths) {
            if (s.endsWith(".jar")) {
                listClasses.addAll(processJarFile(s.trim(), packageName));
            }
        }
        return listClasses;
    }

    /**
     * @param pS
     * @param pPackageName
     */
    private static List<Class> processJarFile(final String jarName, final String packageName) {
        final File file = new File(jarName);
        try {
            final List<String> listClassesName = new ArrayList<>();
            final JarFile jarFile = new JarFile(file);

            final Enumeration<JarEntry> enu = jarFile.entries();
            while (enu.hasMoreElements()) {
                final JarEntry jarEntry = enu.nextElement();
                final String name = jarEntry.getName().replaceAll("/", ".");
                if (name.startsWith(packageName)) {
                    if (!name.contains("$") && (name.endsWith(".class"))) {
                        final String nameCanonique = name.replace(".class", "");

                        listClassesName.add(nameCanonique);
                    }
                }

            }

            jarFile.close();
            return toClasses(listClassesName);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param pListClassesName
     * @return
     */
    private static List<Class> toClasses(final List<String> listClassesName) throws Exception {

        final List<Class> listClasses = new ArrayList<>();
        for (final String className : listClassesName) {
            final Class clazz = UtilJar.class.getClassLoader().loadClass(className);
            listClasses.add(clazz);
            System.out.println("clazz " + clazz.getName());
        }
        return listClasses;
    }

}



