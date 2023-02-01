package rs.ac.uns.ftn.XMLProject.Copyright.service;

import rs.ac.uns.ftn.XMLProject.Copyright.models.a.CopyrightSubmissionRequest;
import rs.ac.uns.ftn.XMLProject.Copyright.models.dto.CopyrightSubmissionRequestDTO;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.XMLProject.Copyright.repository.CopyrightSubmissionRequestRepository;
import org.apache.commons.io.FileUtils;
import rs.ac.uns.ftn.XMLProject.Copyright.util.CopyrightDTOMapper;
import rs.ac.uns.ftn.XMLProject.Copyright.util.CopyrightPDFTransformer;

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
    private CopyrightPDFTransformer copyrightPDFTransformer = new CopyrightPDFTransformer();

    public boolean createCopyrightSubmissionRequest(CopyrightSubmissionRequestDTO copyrightSubmissionRequestDTO) {
        List<CopyrightSubmissionRequest> copyrightSubmissionRequests = copyrightSubmissionRequestRepository.findAll();
        int id = copyrightSubmissionRequests.size() + 1;

        try {
            CopyrightSubmissionRequest request = CopyrightDTOMapper.copyrightSubmissionRequestFromDTO(copyrightSubmissionRequestDTO);
            request.setRequestNumber(String.format("A-%06d", id));
            request.setRequestSubmissionDate(getXMLGregorianCalendarNow());
            copyrightSubmissionRequestRepository.save(request);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return false;
    }

    public CopyrightSubmissionRequestDTO getCopyrightSubmissionRequestById(String id) {
        return CopyrightDTOMapper.copyrightSubmissionRequestToDTO(
                copyrightSubmissionRequestRepository.findById(id)
        );
    }

    public List<CopyrightSubmissionRequestDTO> getAllCopyrightSubmissionRequests() {
        return CopyrightDTOMapper.copyrightSubmissionRequestToDTOList(
                copyrightSubmissionRequestRepository.findAll()
        );
    }

    public ByteArrayInputStream getCopyrightSubmissionRequestPDF(String id) {
        ByteArrayInputStream byteArrayInputStream;

        try {

            copyrightPDFTransformer.generateCopyrightRequestHTML(copyrightSubmissionRequestRepository.findById(id));
            copyrightPDFTransformer.generatePDF(id);

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

            copyrightPDFTransformer.generateCopyrightRequestHTML(copyrightSubmissionRequestRepository.findById(id));

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
