package xml;

import models.p.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PTest {
    public static void test(String testFilePath) {
        try {
            System.out.println("\n\n[INFO] P-1 unmarshalling example.");

            // Definiše se JAXB kontekst (putanja do paketa sa JAXB bean-ovima)
            JAXBContext context = JAXBContext.newInstance("models.p");

            // Unmarshaller je objekat zadužen za konverziju iz XML-a u objektni model
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Unmarshalling generiše objektni model na osnovu XML fajla
            RequestForPatentRecognition request = (RequestForPatentRecognition) unmarshaller.unmarshal(new File(testFilePath));

            // Prikazuje unmarshallovan objekat
            printRequest(request);

            // Promeni vrednosti
            request.getInformationForInstitution().setApplicationNumber("P-9999");
            request.getPatentInformation().getInventor().setEmail("zikazika12@gmail.com");

            // Marshaller je objekat zadužen za konverziju iz objektnog u XML model
            Marshaller marshaller = context.createMarshaller();

            // Podešavanje marshaller-a
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Ispis sadržaja objektnog modela, nakon uspešne validacije
            marshaller.marshal(request, new FileOutputStream("src/test/data/p_new.xml"));

        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("[ERROR] Test failed.");
        }
    }

    private static void printRequest(RequestForPatentRecognition request) {
        // Prikaz broja prijave i datuma podnosenja privaje
        System.out.println("ZAHTEV ZA PRIZNANJE PATENTA\n");
        System.out.printf(
                "Broj prijave: %s\nDatum prijema: %s\t\tPriznati datum podnosenja:%s%n\n",
                request.getInformationForInstitution().getApplicationNumber(),
                request.getInformationForInstitution().getReceiptDate(),
                request.getInformationForInstitution().getSubmissionDate()
        );

        // Request
        PatentInformation.Applicant requestApplicant = request.getPatentInformation().getApplicant();
        System.out.println("PODNOSILAC");
        System.out.printf("Adresa:\n\tGrad: %s\n\tUlica: %s %s\n\tPostanski broj: %s\n",
                requestApplicant.getAddress().getCity(),
                requestApplicant.getAddress().getStreet(), requestApplicant.getAddress().getStreetNumber(),
                requestApplicant.getAddress().getZipCode());
        System.out.printf("Broj telefona: %s\n", requestApplicant.getPhoneNumber());
        System.out.printf("E-posta: %s\n", requestApplicant.getEmail());
        System.out.printf("Broj faksa: %s\n", requestApplicant.getFaxNumber());
        TPerson requestInventor = request.getPatentInformation().getInventor();
        if (requestInventor instanceof TIndividual) {
            TIndividual applicantIndividual = (TIndividual) requestInventor;
            System.out.println("\nPRONALAZAC");
            System.out.printf("Ime: %s\n", applicantIndividual.getFirstName());
            System.out.printf("Prezime: %s\n", applicantIndividual.getLastName());
            System.out.printf(
                    "Adresa:\n\tGrad: %s\n\tUlica: %s %s\n\tPostanski broj: %s\n",
                    applicantIndividual.getAddress().getCity(),
                    applicantIndividual.getAddress().getStreet(), applicantIndividual.getAddress().getStreetNumber(),
                    applicantIndividual.getAddress().getZipCode()
            );
            String citizenship = applicantIndividual.getCitizenship();

            if (citizenship != null) {
                System.out.printf("\tDrzavljanstvo: %s\n", citizenship);
            } else {
                System.out.println("\tDrzavljanstvo: Nije naznaceno");
            }


        }
    }
}
