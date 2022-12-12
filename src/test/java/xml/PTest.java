package xml;

import models.p.Address;
import models.p.RequestForPatentRecognition;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;

public class PTest {
    public static void test() {
        try {
            System.out.println("[INFO] P-1 unmarshalling example.\n");

            // Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
            JAXBContext context = JAXBContext.newInstance("models.p");
            // Unmarshaller je objekat zadužen za konverziju iz XML-a u objektni model
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // XML schema validacija
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("./xml/schemes/p-1.xsd"));

            // Podešavanje unmarshaller-a za XML schema validaciju
            unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(new MyValidationEventHandler());

            // Test: proširiti XML fajl nepostojećim elementom (npr. <test></test>)
            RequestForPatentRecognition request = (RequestForPatentRecognition) unmarshaller.unmarshal(new File("./xml/schemes/p-1.xsd"));

            // Marshaller je objekat zadužen za konverziju iz objektnog u XML model
            Marshaller marshaller = context.createMarshaller();

            // Podešavanje marshaller-a
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Ispis sadržaja objektnog modela, nakon uspešne validacije
            marshaller.marshal(request, System.out);

            // Izmena dokumenta
            request.setProxy(newProxy());

            // Cuvanje izmena dokumenta
            marshaller.marshal(request, new FileOutputStream("./xml/p-1_output.xml"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static RequestForPatentRecognition.Proxy newProxy() {

        RequestForPatentRecognition.Proxy newProxy = new RequestForPatentRecognition.Proxy();

        newProxy.setAddress(new Address());
        newProxy.getAddress().setStreet("Tolstojeva");
        newProxy.getAddress().setStreetNumber(BigInteger.valueOf(40));
        newProxy.getAddress().setCity("Novi Sad");
        newProxy.getAddress().setZipCode(21000);
        newProxy.setPhoneNumber("0645050888");
        newProxy.setFirstName("Pera");
        newProxy.setLastName("Peric");

        return newProxy;
    }

    public static void main(String[] args) {
        test();
    }
}
