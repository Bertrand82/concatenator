package bg.very.simple.mapper;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(name = "mapping")
public class MappingConfiguration {

    private List<ClassPair> paires = new ArrayList<>();

    private List<Class> listClassNonTraitees_1 = new ArrayList<>();

    private List<Class> listClassNonTraitees_2 = new ArrayList<>();

    /**
     *
     */
    public MappingConfiguration() {
        super();
    }

    public MappingConfiguration(final List<ClassPair> pListPaires, final List<Class> pListClassNonTraitees_1,
            final List<Class> pListClassNonTraitees_2) {
        paires = pListPaires;
        listClassNonTraitees_1 = pListClassNonTraitees_1;
        listClassNonTraitees_2 = pListClassNonTraitees_2;
    }

    /**
     * @return Attribut {@link #paires}
     */
    public List<ClassPair> getPaires() {
        return paires;
    }

    /**
     * @param pPaires Valeur à affecter à l'attribut {@link #paires}
     */
    public void setPaires(final List<ClassPair> pPaires) {
        paires = pPaires;
    }

    /**
     * @return Attribut {@link #listClassNonTraitees_1}
     */
    public List<Class> getListClassNonTraitees_1() {
        return listClassNonTraitees_1;
    }

    /**
     * @param pListClassNonTraitees_1 Valeur à affecter à l'attribut {@link #listClassNonTraitees_1}
     */
    public void setListClassNonTraitees_1(final List<Class> pListClassNonTraitees_1) {
        listClassNonTraitees_1 = pListClassNonTraitees_1;
    }

    /**
     * @return Attribut {@link #listClassNonTraitees_2}
     */
    public List<Class> getListClassNonTraitees_2() {
        return listClassNonTraitees_2;
    }

    /**
     * @param pListClassNonTraitees_2 Valeur à affecter à l'attribut {@link #listClassNonTraitees_2}
     */
    public void setListClassNonTraitees_2(final List<Class> pListClassNonTraitees_2) {
        listClassNonTraitees_2 = pListClassNonTraitees_2;
    }

}



