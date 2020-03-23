package bm1.generation.code.old.poubelle;

import java.io.File;
import java.io.IOException;

import com.squareup.javapoet.JavaFile;

/**
 * @author c82bgui
 *
 */
public class JavaPoetWriter {

    File dir = new File("outPut");

    JavaPoetWriter() {
        dir.mkdirs();
    }

    /**
     * @param pJavafile
     */
    public void write(final JavaFile javafile) {
        try {

            javafile.writeTo(dir);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}



/**
 *
 */
