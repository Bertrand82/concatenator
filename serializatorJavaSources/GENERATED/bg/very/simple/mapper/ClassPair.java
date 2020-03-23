package bg.very.simple.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeSpec;

/**
 * @author c82bgui
 *
 */
public class ClassPair {

    private Class c1;

    private Class c2;

    private final String packageName;

    private final List<JavaFile> listJavaFile = new ArrayList<JavaFile>();

    /**
     * @param pC1
     * @param pC2
     */
    public ClassPair(final Class pC1, final Class pC2, final String packageName) {
        c1 = pC1;
        c2 = pC2;
        this.packageName = packageName;
        JavaFile javaFile;
        try {
            javaFile = getJavaFileMapperPojo();
            listJavaFile.add(javaFile);
        } catch (final Exception e) {
            System.err.println("Exception :" + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * @return Attribut {@link #c1}
     */
    public Class getC1() {
        return c1;
    }

    /**
     * @param pC1 Valeur à affecter à l'attribut {@link #c1}
     */
    public void setC1(final Class pC1) {
        c1 = pC1;
    }

    /**
     * @return Attribut {@link #c2}
     */
    public Class getC2() {
        return c2;
    }

    /**
     * @param pC2 Valeur à affecter à l'attribut {@link #c2}
     */
    public void setC2(final Class pC2) {
        c2 = pC2;
    }

    /**
     * @return
     */
    public List<JavaFile> getListJavaFile() {

        return listJavaFile;
    }

    public JavaFile getJavaFileMapperPojo() throws Exception {

        final MethodSpec.Builder methodBuilder1 = getMethodMap(c1, c2);
        final MethodSpec.Builder methodBuilder2 = getMethodMap(c2, c1);

        final TypeSpec.Builder customClassMapperBuilder = TypeSpec.classBuilder(getClassMapperName())
                .addModifiers(Modifier.PUBLIC);

        customClassMapperBuilder.addMethod(methodBuilder1.build());
        customClassMapperBuilder.addMethod(methodBuilder2.build());

        final TypeSpec customClassMapper = customClassMapperBuilder.build();
        final JavaFile javaFile = JavaFile.builder(packageName, customClassMapper).indent("    ").build();

        return javaFile;
    }

    /**
     * @param pC1
     * @param pC2
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    private static Builder getMethodMap(final Class pC1, final Class pC2)
            throws NoSuchFieldException, SecurityException {
        final MethodSpec.Builder methodBuilder1 = MethodSpec.methodBuilder("to" + pC1.getSimpleName())
                .addParameter(pC2, "o").addModifiers(Modifier.PUBLIC).returns(pC1);
        methodBuilder1.addStatement(" $T r = new $T()", pC1, pC1);

        for (final Field field1 : pC1.getDeclaredFields()) {
            Field field2;
            try {
                field2 = pC2.getDeclaredField(field1.getName());
            } catch (final NoSuchFieldException e) {
                field2 = null;
            }
            if (field2 == null) {
            } else if (field1.getType().equals(field2.getType())) {
                final String getter = getGetter(field1);
                if (isMethodExists(getter, pC1)) {
                    methodBuilder1.addStatement("  r." + getSetter(field1) + "(o." + getter + ") ");
                } else {
                    final String getter2 = getGetter2(field1);
                    if (isMethodExists(getter2, pC1)) {
                        methodBuilder1.addStatement("  r." + getSetter(field1) + "(o." + getter2 + ") ");
                    }
                }
            } else {
                if (field1.getType().isPrimitive()) {

                } else {
                    methodBuilder1.addStatement("  r." + getSetter(field1) + "(null)");
                }

            }
        }
        methodBuilder1.addStatement("return r ", pC1);
        return methodBuilder1;
    }

    private static String getGetter2(final Field field) {
        final String name = capitalizeFirstLetter(field.getName());
        final String methodName = "is" + name + "()";
        return methodName;
    }

    /**
     * @param pField1
     * @return
     */
    private static String getGetter(final Field field) {
        final String name = capitalizeFirstLetter(field.getName());
        final String methodName = "get" + name + "()";
        return methodName;
    }

    private static boolean isMethodExists(String name, final Class clazz) {
        if (name == null) {
            return false;
        }
        final int i = name.indexOf("(");
        if (i > 0) {
            name = name.substring(0, i);

        }
        for (final Method m : clazz.getMethods()) {
            if (m.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private static String getSetter(final Field field) {
        final String name = capitalizeFirstLetter(field.getName());
        return "set" + name;
    }

    /**
     * @param pName
     * @return
     */
    private static String capitalizeFirstLetter(final String str) {
        final String s = str.substring(0, 1).toUpperCase() + str.substring(1);
        return s;
    }

    /**
     * @param pField1
     * @param pC2
     * @return
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    private Builder getMethodBuilder1(final Field field1, final Class pC2)
            throws NoSuchFieldException, SecurityException {

        final Field field2 = c2.getDeclaredField(field1.getName());
        final MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("to" + field1.getName())
                .addParameter(field2.getType(), "s").addModifiers(Modifier.PUBLIC).returns(field1.getType());
        if (isSimpleNativeField(field1.getType()) && isSimpleNativeField(field2.getType())) {

        }
        return methodBuilder;
    }

    /**
     * @param pType
     * @return
     */
    private boolean isSimpleNativeField(final Class<?> type) {
        if (type.isPrimitive()) {
            return true;
        }
        if (type.equals(String.class)) {
            return true;
        }
        return false;
    }

    /**
     * @return
     */
    private String getClassMapperName() {

        return "Mapper_" + c1.getSimpleName();
    }

}



/**
 *
 */
