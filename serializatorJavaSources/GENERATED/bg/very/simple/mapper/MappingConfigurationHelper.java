package bg.very.simple.mapper;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class MappingConfigurationHelper {

    public static String toStringXml(final MappingConfiguration mappingConfiguration) throws Exception {
        final JAXBContext jc = JAXBContext.newInstance(MappingConfiguration.class);
        final javax.xml.bind.Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
        final StringWriter sw = new StringWriter();
        marshaller.marshal(mappingConfiguration, sw);
        final String xmlContent = sw.toString();
        return xmlContent;
    }

    public static void printConfiguration(final MappingConfiguration mappingConfiguration) {
        try {
            final String s = toStringXml(mappingConfiguration);
            System.err.println("xml:" + s);
            final File f = new File("CONFIGURATION.xml");
            final FileWriter fw = new FileWriter(f);
            fw.write(s);
            fw.close();
            System.err.println("Print to :" + f.getName() + " exists: " + f.exists());
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static MappingConfiguration readConfiguration(final File file) {
        try {
            final JAXBContext jc = JAXBContext.newInstance(MappingConfiguration.class);
            final Unmarshaller unmarshaller = jc.createUnmarshaller();
            unmarshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
            final Object o = unmarshaller.unmarshal(file);
            return (MappingConfiguration) o;
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}



/**
 *
 */
