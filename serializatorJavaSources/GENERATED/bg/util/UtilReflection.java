package bg.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class UtilReflection {

    /**
     * Instancie un objet avec des valeurs par defaut pour les String
     *
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Object createObjet(final Class<?> clazz) throws Exception {
        return createObjet(clazz, true);
    }

    /**
     * Cree un objet avec eventuellement des valeurs par defauts pour les String
     *
     * @param clazz
     * @param populateString
     * @return
     * @throws Exception
     */
    public static Object createObjet(final Class<?> clazz, final boolean populateString) throws Exception {

        final Object o = clazz.getConstructor().newInstance();
        int i = 0;
        for (final Field f : clazz.getDeclaredFields()) {
            if (f.getType().equals(String.class)) {
                f.setAccessible(true);
                if (populateString) {
                    final String value = "AZERTY_" + i++ + "_" + f.getName();
                    f.set(o, value);
                }
            } else {

                if (f.getType().getPackage() == null) {
                } else if (f.getType().getPackage().getName().indexOf("atom") > 0) {
                    final Object oo = createObjet(f.getType(), populateString);
                    f.setAccessible(true);
                    f.set(o, oo);
                }
            }
        }
        return o;
    }

    /**
     * methode toString commede pour debuger
     *
     * @param o
     * @return
     * @throws Exception
     */
    public static String toString(final Object o) throws Exception {
        return toString(o, "");
    }

    private static String toString(final Object o, final String marge) throws Exception {

        String s = o.getClass().getName() + "\n";
        final int i = 0;
        for (final Field f : o.getClass().getDeclaredFields()) {
            if (f.getType().equals(String.class)) {
                f.setAccessible(true);
                s += marge + "   " + f.getName() + " : " + f.get(o) + "\n";

            } else {
                final boolean isCollection = Collection.class.isAssignableFrom(f.getType());
                if (isCollection) {
                    s += "  List " + f.getName() + " xxxxxxxxxxxxxxxxxxxxxxx   warning";
                }
                if (f.getType().getPackage() == null) {
                } else if (f.getType().getPackage().getName().indexOf("atom") > 0) {
                    f.setAccessible(true);
                    final Object oo = f.get(o);
                    s += "\n" + marge + toString(oo, marge + "   ");
                }
            }
        }
        return s;
    }

    /**
     * Verifie que 2 objets de classe differentes mais "similaire" , c'est a dire ayant les mêmes nom de champs , sont
     * "egaux"
     *
     * @param o1
     * @param o2
     * @return
     * @throws Exception
     */
    public static boolean equals(final Object o1, final Object o2) throws Exception {
        if (o1 == null) {
            return o2 == null;
        }
        if (o2 == null) {
            return false;
        }
        if (o2.getClass().isPrimitive()) {
            return o2 == o1;
        }
        if (isSimpleType(o1)) {
            return o1.equals(o2);
        }
        if (isSimpleType(o2)) {
            return o2.equals(o1);
        }
        if (isList(o1)) {
            if (isList(2)) {
                final List l1 = (List) o1;
                final List l2 = (List) o2;
                for (int i = 0; i < l1.size(); i++) {
                    if (i >= l2.size()) {
                        return false;
                    }
                    if (!equals(l1.get(i), l2.get(i))) {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }

        for (final Field f1 : o1.getClass().getDeclaredFields()) {
            final Field f2 = getFieldFromClazz(o2.getClass(), f1.getName());
            f1.setAccessible(true);
            f2.setAccessible(true);
            if (!equals(f1.get(o1), f2.get(o2))) {
                return false;
            }

        }
        return true;
    }

    private static Field getFieldFromClazz(final Class clazz, final String pName) {
        for (final Field f : clazz.getDeclaredFields()) {
            if (f.getName().equals(pName)) {
                return f;
            }
        }
        return null;
    }

    private static boolean isList(final Object o) {

        final Class<?> clazz = o.getClass();
        return (clazz.equals(List.class)) || (clazz.equals(ArrayList.class));

    }

    private static boolean isSimpleType(final Object o) {
        final Class<?> clazz = o.getClass();
        return (clazz.equals(String.class)) || (clazz.equals(Integer.class) || (clazz.equals(Double.class))
                || (clazz.equals(Boolean.class)) || (clazz.equals(Float.class)) || (clazz.equals(Date.class)));
    }

}

 wwwwwwwwwwwwww very wwwwwwwwwww

 wwwwwwwwwwwwww simple wwwwwwwwwww

 wwwwwwwwwwwwww mapper wwwwwwwwwww



/**
 *
 */
