package bg.mapper;

import java.util.ArrayList;
import java.util.List;

public class MainGeneratorMapper {

    public static void main(final String[] args) {
        final Class clazz1 = fr.msa.atom.sante.prestationsennature.facturepn.objets.factureatom.v1.InfoFactureCalculee.class;
        final Class clazz2 = fr.msa.atom.sante.prestationsennature.facturepn.objets.v1.InfoFactureCalculee.class;
        final ClassPair cp = new ClassPair(clazz1, clazz2);
        final List<ClassPair> listPair = new ArrayList<ClassPair>();
        final ClassMapperGenerator mapperGenerator = new ClassMapperGenerator(listPair);
    }

}

 wwwwwwwwwwwwww poet wwwwwwwwwww



/**
 *
 */
