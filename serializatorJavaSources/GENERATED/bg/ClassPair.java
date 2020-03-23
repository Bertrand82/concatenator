package bg;

/**
 * @author c82bgui
 *
 */
public class ClassPair {

    Class c1;

    Class c2;

    /**
     * @param pC1
     * @param pC2
     */
    public ClassPair(final Class pC1, final Class pC2) {
        c1 = pC1;
        c2 = pC2;
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

}



