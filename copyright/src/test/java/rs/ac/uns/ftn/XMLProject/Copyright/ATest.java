package java.rs.ac.uns.ftn.XMLProject.Copyright;

import rs.ac.uns.ftn.XMLProject.Copyright.models.a.*;
import rs.ac.uns.ftn.XMLProject.Copyright.models.a.CopyrightSubmissionRequest;
import rs.ac.uns.ftn.XMLProject.Copyright.models.a.TIndividual;
import rs.ac.uns.ftn.XMLProject.Copyright.models.a.TPerson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileOutputStream;

public class ATest {

    public static void test(String testFilePath) {

        System.out.println("[INFO] A-1 unmarshalling/marshalling example");
        try {
            // Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
            JAXBContext context = JAXBContext.newInstance("models.a");

            // Unmarshaller je objekat zadužen za konverziju iz XML-a u objektni model
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Unmarshalling generiše objektni model na osnovu XML fajla
            CopyrightSubmissionRequest request =
                    (CopyrightSubmissionRequest) unmarshaller
                            .unmarshal(new File(testFilePath));

            // Prikazuje unmarshallovan objekat
            printRequest(request);

            // Promeni vrednosti
            request.setRequestNumber("A-1337");
            request.getApplicant().setEmail("novimail@gmail.com");
            request.getWorkTitle().setMainTitle("Isn't she lovely obrada");
            request.setFormOfRecordingWork("CD");

            // Marshaller je objekat zadužen za konverziju iz objektnog u XML model
            Marshaller marshaller = context.createMarshaller();

            // Podešavanje marshaller-a
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Umesto System.out-a, može se koristiti FileOutputStream
            marshaller.marshal(request, new FileOutputStream("src/test/data/a_new.xml"));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[ERROR] Test failed.");
        }

    }

    private static void printRequest(CopyrightSubmissionRequest request) {
        // Prikaz broja prijave i datuma podnosenja privaje
        System.out.println("Zahtev Za Unosenje U Evidenciju I Deponovanje Autorskih Dela");
        System.out.printf(
                "Broj prijave: %s\tDatum podnosenja prijave: %s%n",
                request.getRequestNumber(),
                request.getRequestSubmissionDate()
        );

        // Request
        TPerson requestApplicant = request.getApplicant();
        if (requestApplicant instanceof TIndividual) {
            TIndividual applicantIndividual = (TIndividual) requestApplicant;
            System.out.println("Podnosilac je fizicko lice.");
            System.out.printf("Ime: %s\n", applicantIndividual.getFirstName());
            System.out.printf("Prezime: %s\n", applicantIndividual.getLastName());
            System.out.printf(
                    "Adresa:\n\tGrad: %s\n\tUlica: %s\n\tPostanski broj: %s\n",
                    applicantIndividual.getAddress().getCity(),
                    applicantIndividual.getAddress().getStreet()+applicantIndividual.getAddress().getStreetNumber(),
                    applicantIndividual.getAddress().getZipCode()
            );
            String citizenship = applicantIndividual.getCitizenship();

            if (citizenship != null) {
                System.out.printf("Drzavljanstvo: %s\n", citizenship);
            } else {
                System.out.println("Drzavljanstvo: Nije naznaceno");
            }


        } else {
            System.out.println("Podnosilac je pravno lice.");
        }
    }

}
