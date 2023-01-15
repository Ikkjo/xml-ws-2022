package services;

import repository.RequestForPatentRecognitionRepository;
import util.PDFTransformer;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;

public class PatentService {

    private RequestForPatentRecognitionRepository repository = new RequestForPatentRecognitionRepository();
    private PDFTransformer pdfTransformer = new PDFTransformer();

    public ByteArrayInputStream getPdfZahtev(String id) {
        ByteArrayInputStream byteArrayInputStream;

        try {

            pdfTransformer.generateHTML(repository.findById(id));
            pdfTransformer.generatePDF(id);

            File pdfFile = new File("gen/pdf/" + id + ".pdf");
            File htmlFile = new File("gen/html/" + id + ".html");
            File xmlFile = new File("gen/xml/" + id + ".xml");
            byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(pdfFile));
            pdfFile.delete();
            htmlFile.delete();
            xmlFile.delete();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return byteArrayInputStream;
    }

    public String getHtmlZahtev(String id) {
        String fileContent = "";

        try {

            pdfTransformer.generateHTML(repository.findById(id));

            File htmlFile = new File("gen/html/" + id + ".html");
            File xmlFile = new File("gen/xml/" + id + ".xml");
            fileContent = FileUtils.readFileToString(htmlFile, StandardCharsets.UTF_8);
            htmlFile.delete();
            xmlFile.delete();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return fileContent;
    }
}
