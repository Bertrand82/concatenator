package bm1.generation.code.old.poubelle;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * @author c82bgui
 *
 */
public class UtilPoet {

    /**
     * @param pC1
     * @param pC2
     */
    public static JavaFile generateInterfaceMapper(final Class c1, final Class c2) {
        final String nameMapper = c1.getSimpleName() + "Mapper";
        final TypeSpec interfaceMapper = TypeSpec.interfaceBuilder(nameMapper).addModifiers(Modifier.PUBLIC)
                .addMethod(MethodSpec.methodBuilder("map").addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC).returns(c2)
                        .addParameter(c1, "c1").build())
                .addMethod(MethodSpec.methodBuilder("map").addModifiers(Modifier.ABSTRACT, Modifier.PUBLIC).returns(c1)
                        .addParameter(c2, "c2").build())
                .build();

        final JavaFile javaFile = JavaFile.builder("com.bg.generted", interfaceMapper).indent("    ").build();
        return javaFile;
    }

}



/**
 *
 */
