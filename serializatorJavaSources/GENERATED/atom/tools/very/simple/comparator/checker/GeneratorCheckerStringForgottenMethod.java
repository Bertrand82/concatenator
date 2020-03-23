package atom.tools.very.simple.comparator.checker;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.MethodSpec;

import fr.msa.atom.sante.prestationsennature.facturepn.types.v1.DateNaissanceAtom;

public class GeneratorCheckerStringForgottenMethod {

    private final List<ClassPair> listClassPair = new ArrayList<ClassPair>();

    private final List<Field> listList = new ArrayList<Field>();

    private final List<Field> listListString = new ArrayList<Field>();

    private final List<Field> listString = new ArrayList<>();

    private final List<Field> listField = new ArrayList<>();

    private final Class clazzOut;

    private final Class clazzIn;

    public GeneratorCheckerStringForgottenMethod(final ClassPair cPair) {
        cPair.setAlreadyProcessed(true);

        clazzIn = cPair.classIn;
        clazzOut = cPair.classOut;
        for (final Field fieldOut : clazzOut.getDeclaredFields()) {
            final Field fieldIn = getFieldFromClass(clazzIn, fieldOut.getName());
            if (fieldOut.getType().isEnum()) {

            } else if (fieldOut.getType().isPrimitive()) {

            } else if (fieldOut.getType().equals(String.class)) {
                if (getterExistForField(clazzOut, fieldOut)) {
                    listString.add(fieldOut);
                }
            } else if (fieldOut.getType().equals(Double.class)) {

            } else if (fieldOut.getType().equals(Integer.class)) {

            } else if (fieldOut.getType().equals(Boolean.class)) {

            } else if (fieldOut.getType().equals(Date.class)) {

            } else if (fieldOut.getType().equals(ClassLoader.class)) {

            } else if (fieldOut.getType().equals(Class.class)) {

            } else if (fieldOut.getType().getPackage() == null) {

            } else if ((fieldOut.getType().getPackage().getName() + "").startsWith("sun.reflect.")) {

            } else if (fieldOut.getType().getPackage().getName().startsWith("sun.")) {

            } else if (fieldOut.getType().isArray()) {

            } else if (fieldOut.getType().equals(DateNaissanceAtom.class)) {

            } else if (fieldOut.getType().equals(List.class)) {

                final Class<?> cOut = getGenericTypeFromList(fieldOut);
                final Class<?> cIn = getGenericTypeFromList(fieldIn);
                if (cOut.equals(String.class)) {
                    listListString.add(fieldOut);
                } else {
                    listList.add(fieldOut);
                    final ClassPair cp = new ClassPair(cIn, cOut);
                    listClassPair.add(cp);
                }

            } else {
                if (getterExistForField(clazzOut, fieldOut)) {
                    final ClassPair cp = new ClassPair(fieldIn.getType(), fieldOut.getType());
                    listClassPair.add(cp);
                    listField.add(fieldOut);
                }
            }
        }
    }

    private Field getFieldFromClass(final Class clazz, final String fieldName) {
        for (final Field f : clazz.getDeclaredFields()) {
            if (f.getName().equals(fieldName)) {
                return f;
            }
        }
        return null;
    }

    private Class<?> getGenericTypeFromList(final Field field) {
        final ParameterizedType listType = (ParameterizedType) field.getGenericType();
        final Class<?> c = (Class<?>) listType.getActualTypeArguments()[0];
        return c;

    }

    private boolean getterExistForField(final Class pClazz, final Field field) {
        final String getter = getGetter(field).replace("()", "");
        for (final Method m : clazzOut.getMethods()) {
            if (m.getName().equals(getter)) {
                return true;
            }
        }
        return false;
    }

    public MethodSpec getMethodCompare() {
        final MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(prefixMethod + clazzOut.getSimpleName())
                .addParameter(clazzOut, "out").addParameter(clazzIn, "in").addModifiers(Modifier.PUBLIC);
        methodBuilder.beginControlFlow("if (out == null)");
        methodBuilder.addStatement("return");
        methodBuilder.endControlFlow();
        methodBuilder.beginControlFlow("if (in == null)");
        methodBuilder.addStatement("return");
        methodBuilder.endControlFlow();

        methodBuilder.addStatement("//Nombre de  String :  " + listString.size());
        for (final Field fString : listString) {
            final String name = fString.getName();
            methodBuilder.addStatement("// String " + name);
            methodBuilder.beginControlFlow(
                    "if ((out." + getGetter(fString) + "== null) &&  (in." + getGetter(fString) + "!= null))");
            methodBuilder.addStatement("String value =in." + getGetter(fString));
            methodBuilder.beginControlFlow("if ( this.rapportManager.isNonMapped(value))");
            methodBuilder.addStatement("out." + getSetter(fString) + "(value)");
            methodBuilder.endControlFlow();
            methodBuilder.endControlFlow();
        }

        for (final Field field : listField) {
            methodBuilder.addStatement("//F  " + field.getName() + "  " + field.getType().getSimpleName());
            methodBuilder.addStatement(prefixMethod + field.getType().getSimpleName() + "(out." + getGetter(field)
                    + ", in." + getGetter(field) + ")");

        }

        for (final Field field : listList) {
            methodBuilder.addStatement("//L  " + field.getName() + "  list " + getGenericTypeFromList(field));
            final Class<?> c = getGenericTypeFromList(field);
            final String getter = getGetter(field);
            methodBuilder.beginControlFlow("for(int i=0; i<in." + getter + ".size();i++)");
            methodBuilder.addStatement(
                    prefixMethod + c.getSimpleName() + "( out." + getter + ".get(i), in." + getter + ".get(i))");
            methodBuilder.endControlFlow();

        }
        for (final Field field : listListString) {
            methodBuilder.addStatement(
                    "//LS  " + field.getName() + "  list " + getGenericTypeFromList(field) + " No Implemented");

        }

        return methodBuilder.build();

    }

    /**
     * @return Attribut {@link #listClassPair}
     */
    public List<ClassPair> getListClassPair() {
        return listClassPair;
    }

    /**
     * @return Attribut {@link #clazz}
     */
    public Class getClazz() {
        return clazzOut;
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

    /**
     * @param pField1
     * @return
     */
    private static String getSetter(final Field field) {
        final String name = capitalizeFirstLetter(field.getName());
        final String methodName = "set" + name;
        return methodName;
    }

    /**
     * @param pName
     * @return
     */
    private static String capitalizeFirstLetter(final String str) {
        final String s = str.substring(0, 1).toUpperCase() + str.substring(1);
        return s;
    }

    private static String prefixMethod = "checkStringForgottenIn_";

}



