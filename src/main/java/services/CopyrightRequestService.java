package services;

import models.a.CopyrightSubmissionRequest;
import models.a.TIndividual;
import models.a.TPerson;
import models.a.dto.CopyrightSubmissionRequestDTO;
import org.springframework.stereotype.Service;
import repository.CopyrightSubmissionRequestRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Transformer;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class CopyrightRequestService {

    private CopyrightSubmissionRequestRepository copyrightSubmissionRequestRepository = new CopyrightSubmissionRequestRepository();
//    private Converter converter = new Converter();
//    private PDFTransformer pdfTransformer = new PDFTransformer();

    public boolean createCopyrightSubmissionRequest(CopyrightSubmissionRequestDTO copyrightSubmissionRequestDTO) {
        return false;
    }

    public CopyrightSubmissionRequestDTO getCopyrightSubmissionRequestById(String id) {
        return null;
    }

    public List<CopyrightSubmissionRequestDTO> getAllCopyrightSubmissionRequests() {
        return null;
    }

    public ByteArrayInputStream getCopyrightSubmissionRequestPDF(String id) {
        ByteArrayInputStream byteArrayInputStream;

        try {

//            pdfTransformer.generateHTML(patentRepository.findById(id));
//            pdfTransformer.generatePDF(id);

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

    public String getCopyrightSubmissionRequestHTML(String id) {
        String fileContent = "";

        try {

//            pdfTransformer.generateHTML(copyrightSubmissionRequestRepository.findById(id));

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
