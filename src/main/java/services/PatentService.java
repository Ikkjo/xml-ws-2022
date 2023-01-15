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
            byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(pdfFile));
            pdfFile.delete();
            htmlFile.delete();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return byteArrayInputStream;
    }

    public String getHtmlZahtev(String id) {
        String fileContent = null;

        try {

            pdfTransformer.generateHTML(repository.findById(id));

            File htmlFile = new File("gen/html/" + id + ".html");
            fileContent = FileUtils.readFileToString(htmlFile, StandardCharsets.UTF_8);
            htmlFile.delete();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return fileContent;
    }
}
