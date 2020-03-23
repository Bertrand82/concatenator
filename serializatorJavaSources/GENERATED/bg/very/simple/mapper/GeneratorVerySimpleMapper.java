package bg.very.simple.mapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.squareup.javapoet.JavaFile;

import bg.util.UtilPackage;

/**
 * @author c82bgui
 *
 */
public class GeneratorVerySimpleMapper {

    List<Class> listClassNonTraitees_1 = new ArrayList<Class>();

    List<Class> listClassNonTraitees_2 = new ArrayList<Class>();

    private final JavaPoetWriter javaPoetWritter;

    private String packageGenerated = "bg";

    /**
     * @param pDirOutput
     * @param pPackage1
     * @param pPackage2
     * @param pString
     */
    public GeneratorVerySimpleMapper(final File pDirOutput, final String packageName1, final String packageName2,
            final String packageGenerated) {
        this(pDirOutput, UtilPackage.getClassesFromPackage(packageName1),
                UtilPackage.getClassesFromPackage(packageName2), packageGenerated);
    }

    /**
     * @param pDirOutput
     * @param pClassesFromPackage
     * @param pClassesFromPackage2
     * @param pPackageGenerated
     */
    public GeneratorVerySimpleMapper(final File dirOutput, final List<Class> listClass1, final List<Class> listClass2,
            final String pPackageGenerated) {
        System.err.println("listClass1 " + listClass1.size());
        System.err.println("listClass2 " + listClass2.size());
        javaPoetWritter = new JavaPoetWriter(dirOutput);
        packageGenerated = pPackageGenerated;
        processListSubClass(listClass1, listClass2);
        processXml();

    }

    private void processXml() {

        final MappingConfiguration mappingConfiguration = new MappingConfiguration(listPaires, listClassNonTraitees_1,
                listClassNonTraitees_2);
        MappingConfigurationHelper.printConfiguration(mappingConfiguration);
    }

    /**
     * @param pListSubClass1
     * @param pListSubClass2
     */
    private List<ClassPair> listPaires;

    private void processListSubClass(final List<Class> listSubClass1, final List<Class> listSubClasses2) {
        listPaires = initClassPair(listSubClass1, listSubClasses2);
        for (final ClassPair cp : listPaires) {
            processPair(cp);
        }

        System.err.println("\nNb de mappage " + listPaires.size());
        System.err.println("Write to  " + javaPoetWritter.getDirOutput().getAbsolutePath());
    }

    /**
     * @param pCp
     */
    private void processPair(final ClassPair classPair) {

        for (final JavaFile javafile : classPair.getListJavaFile()) {
            javaPoetWritter.write(javafile);
        }

    }

    private List<ClassPair> initClassPair(final List<Class> listSubClass1, final List<Class> listSubClasses2) {
        final List<ClassPair> listClassPairs = new ArrayList<ClassPair>();
        for (final Class c1 : listSubClass1) {
            final Class c2 = getClassFromSimpleList(c1, listSubClasses2);
            if (c1.getSimpleName().equals("package-info")) {
            } else if (c2 == null) {
                listClassNonTraitees_1.add(c1);
                System.err.println("No class pair for c1: " + c1.getName() + "   " + c1.getName());
            } else if (c1.getName().equals(c2.getName())) {
                System.err.println("No class pair for c1: " + c1.getName() + " equals   " + c2.getName());
            } else {
                final ClassPair classPair = new ClassPair(c1, c2, packageGenerated);
                listClassPairs.add(classPair);
            }
        }
        for (final Class c2 : listSubClasses2) {
            if (!isPaired_2(c2, listClassPairs)) {
                listClassNonTraitees_2.add(c2);
            }
        }
        return listClassPairs;
    }

    private boolean isPaired_2(final Class pC2, final List<ClassPair> pListClassPairs) {
        for (final ClassPair cp : pListClassPairs) {
            if (cp.getC2().equals(pC2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param pC1
     * @param pListSubClasses2
     * @return
     */
    private Class getClassFromSimpleList(final Class c1, final List<Class> listSubClasses2) {
        final Class clazz = PropertiesClasses.getClassesPair(c1.getName());
        if (clazz != null) {
            return clazz;
        }
        final List<Class> lClass = getListClassBySimpleName(c1.getSimpleName(), listSubClasses2);
        if (lClass.size() == 0) {
            System.err.println("No Class for : " + c1.getSimpleName());
            return null;
        } else if (lClass.size() == 1) {
            return lClass.get(0);
        } else {
            return bestChoiceFromList(c1, lClass);
        }

    }

    /**
     * @param pSimpleName
     * @param pListSubClasses2
     * @return
     */
    private List<Class> getListClassBySimpleName(final String pSimpleName, final List<Class> pListSubClasses2) {
        final List<Class> list = new ArrayList<>();
        for (final Class c : pListSubClasses2) {
            if (c.getSimpleName().equals(pSimpleName)) {
                list.add(c);
            }
        }
        return list;
    }

    /**
     * @param pC1
     * @param pLClass
     * @return
     */
    private Class bestChoiceFromList(final Class pC1, final List<Class> listClass) {
        // Les classes ont toutes le meme simpleNom .
        // Quel critère ? Le nombre de champs ? le nom de package ?
        // Les noms des champs ?
        // TODO Faite une class UtilCompareClass avec les methodes qui vont bien
        return listClass.get(0);
    }

}



