package bm1.generation.code.old.poubelle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author c82bgui
 *
 */
public class UtilReflection {

    public static void main(final String[] a) {
        final List<Class> list = getSubClasses(UtilReflection.class);
        System.out.println("list " + list.size());
    }

    /**
     * @param pClazz1
     * @return
     */
    public static List<Class> getSubClasses(final Class clazz1) {
        final List<Class> listClasses = new ArrayList<>();
        final Package package1 = clazz1.getPackage();
        System.err.println("getSubClasses " + clazz1);
        final String packageName = package1.getName();
        System.err.println("getSubClasses " + package1.getName());

        // System.err.println("java.class.path : " + System.getProperty("java.class.path"));
        final String javaClassPaths[] = System.getProperty("java.class.path").split(";");
        for (final String s : javaClassPaths) {
            if (s.endsWith(".jar")) {
                // processJarFile(s, clazz1);
            } else {
                final File dirClasses = new File(s);
                final File dirPackage = new File(dirClasses, getPackagePath(clazz1));
                if (dirPackage.exists()) {
                    final String[] names = dirPackage.list();
                    for (final String name : names) {

                        if ((name.indexOf("$") < 0) && (name.indexOf(".class") > 0)) {
                            final String sName = name.substring(0, name.indexOf("."));
                            final String className = clazz1.getPackage().getName() + "." + sName;

                            try {
                                final Class clazz = UtilReflection.class.getClassLoader().loadClass(className);
                                listClasses.add(clazz);
                            } catch (final ClassNotFoundException e) {
                                System.out.println("ClassNotFound :" + e.getMessage());
                            }
                        }
                    }

                }

            }
        }
        return listClasses;
    }

    /**
     * @param pJarFile
     */
    private static void processJarFile(final String s, final Class clazz) {
        try {
            final JarFile jarFile = new JarFile(s);
            final JarEntry jarEntryClazz = jarFile.getJarEntry(clazz.getName().replaceAll(".", "/") + ".class");
            if (jarEntryClazz != null) {
                System.err.println("BINGO BINGO " + jarFile.getName());
            }
            final Enumeration<JarEntry> entries = jarFile.entries(); //gives ALL entries in jar
            while (entries.hasMoreElements()) {
                final String name = entries.nextElement().getName();
                final String packageName = getPackagePath(clazz);
                if (name.startsWith(packageName)) { //filter according to the path
                    System.err.println("YYYYYYYYYYYYYYYYES :" + name + "   " + jarFile.getName());
                }
            }
            jarFile.close();
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static String getPackagePath(final Class clazz) {
        final String packageName = clazz.getPackage().getName().replaceAll("\\.", "/");;
        return packageName;
    }

}

0 checker  ClassPair.java
1 checker  GeneratorCheckerStringForgottenClass.java
2 checker  GeneratorCheckerStringForgottenMethod.java
3 checker  MainGeneratorChecker.java
4 bg  ClassFinder.java
5 bg  ClassPair.java
6 bg  JavaPoetWriter.java
7 justification  MainHelperJustification.java
8 bg  MainConcatene.java
9 mapper  ClassMapperGenerator.java
10 mapper  ClassPair.java
11 mapper  GeneratorMapperMethod.java
12 helper  MainListClassFromPackage.java
13 mapper  MainGeneratorMapper.java
14 bg  PropertiesClasses.java
15 util  MainGetClassesFromPackage.java
16 util  UtilJar.java
17 util  UtilPackage.java
18 util  UtilReflection.java
19 mapper  ClassPair.java
20 mapper  GeneratorVerySimpleMapper.java
21 mapper  JavaPoetWriter.java
22 mapper  MainMapperTest.java
23 mapper  MappingConfiguration.java
24 mapper  MappingConfigurationHelper.java
25 mapper  PropertiesClasses.java
26 poubelle  JavaPoetWriter.java
27 poubelle  MainGenerateAbstrctMapper.java
28 poubelle  UtilPoet.java
29 poubelle  UtilReflection.java

Picked up JAVA_TOOL_OPTIONS: -Dfile.encoding=UTF8

