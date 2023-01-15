package service;

import models.a.CopyrightSubmissionRequest;
import models.a.dto.CopyrightSubmissionRequestDTO;
import org.springframework.stereotype.Service;
import repository.CopyrightSubmissionRequestRepository;
import org.apache.commons.io.FileUtils;
import util.DTOUtils;
import util.DocumentTransformer;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.io.File;
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
        List<CopyrightSubmissionRequest> copyrightSubmissionRequests = copyrightSubmissionRequestRepository.findAll();
        int id = copyrightSubmissionRequests.size() + 1;

        try {
            CopyrightSubmissionRequest request = DTOUtils.copyrightSubmissionRequestfromDTO(copyrightSubmissionRequestDTO);
            request.getInformationForInstitution().setRequestNumber(String.format("A-%06d", id));
            request.getInformationForInstitution().setRequestSubmissionDate(getXMLGregorianCalendarNow());
            copyrightSubmissionRequestRepository.save(request);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return false;
    }

    public CopyrightSubmissionRequestDTO getCopyrightSubmissionRequestById(String id) {
        return DTOUtils.copyrightSubmissionRequestToDTO(
                copyrightSubmissionRequestRepository.findById(id)
        );
    }

    public List<CopyrightSubmissionRequestDTO> getAllCopyrightSubmissionRequests() {
        return DTOUtils.copyrightSubmissionRequestToDTOList(
                copyrightSubmissionRequestRepository.findAll()
        );
    }

    public ByteArrayInputStream getCopyrightSubmissionRequestPDF(String id) {
        ByteArrayInputStream byteArrayInputStream;

        try {

            DocumentTransformer.generateXHTML(copyrightSubmissionRequestRepository.findById(id));
            DocumentTransformer.generatePDF(id);

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

            DocumentTransformer.generateXHTML(copyrightSubmissionRequestRepository.findById(id));

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

    private XMLGregorianCalendar getXMLGregorianCalendarNow() throws DatatypeConfigurationException {
        Date now = new Date();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(now);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    }
}
