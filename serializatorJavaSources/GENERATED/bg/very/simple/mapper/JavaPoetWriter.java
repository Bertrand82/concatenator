package bg.very.simple.mapper;

import java.io.File;
import java.io.IOException;

import com.squareup.javapoet.JavaFile;

/**
 * @author c82bgui
 *
 */
public class JavaPoetWriter {

    private final File dirOutput;

    public JavaPoetWriter(final File dir) {
        dirOutput = dir;
        dir.mkdirs();
    }

    /**
     * @param pJavafile
     */
    public void write(final JavaFile javafile) {
        try {

            javafile.writeTo(dirOutput);

        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public File getDirOutput() {
        return dirOutput;
    }

}



/**
 *
 */
