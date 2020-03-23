package atom.tools.very.simple.comparator.checker;

public class ClassPair {

    public Class classIn;

    public Class classOut;

    boolean isAlreadyProcessed = false;

    public ClassPair() {
    }

    public ClassPair(final Class pClassIn, final Class pClassOut) {
        super();
        classIn = pClassIn;
        classOut = pClassOut;
    }

    /**
     * @return Attribut {@link #isAlreadyProcessed}
     */
    public boolean isAlreadyProcessed() {
        return isAlreadyProcessed;
    }

    /**
     * @param pIsAlreadyProcessed Valeur à affecter à l'attribut {@link #isAlreadyProcessed}
     */
    public void setAlreadyProcessed(final boolean pIsAlreadyProcessed) {
        isAlreadyProcessed = pIsAlreadyProcessed;
    }

}



