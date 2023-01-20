package services;

import models.p.dto.RequestForPatentRecognitionDTO;
import repository.RequestForPatentRecognitionRepository;
import util.PDFTransformer;
import org.apache.commons.io.FileUtils;
import util.PatentDTOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PatentService {

    private RequestForPatentRecognitionRepository repository = new RequestForPatentRecognitionRepository();
    private PDFTransformer pdfTransformer = new PDFTransformer();
    private PatentDTOUtils dtoUtils = new PatentDTOUtils();

    public ByteArrayInputStream getRequestForPatentRecognitionPDF(String id) {
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

    public String getRequestForPatentRecognitionHTML(String id) {
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

    public List<RequestForPatentRecognitionDTO> getAllPatentRecognitionRequests() throws Exception {
        return dtoUtils.patentRecognitionRequestsToDTOList(repository.getAll());
    }

    public RequestForPatentRecognitionDTO getPatentRecognitionRequest(String id) throws Exception {
        return dtoUtils.patentRecognitionRequestToDTO(repository.findById(id));
    }
}
