package rs.ac.uns.ftn.XMLProject.Copyright.service;

import org.springframework.http.ResponseEntity;
import rs.ac.uns.ftn.XMLProject.Copyright.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.XMLProject.Copyright.models.a.CopyrightSubmissionRequest;
import rs.ac.uns.ftn.XMLProject.Copyright.models.dto.CopyrightSubmissionRequestDTO;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.XMLProject.Copyright.models.solution.CopyrightRequestSolution;
import rs.ac.uns.ftn.XMLProject.Copyright.repository.CopyrightSubmissionRequestRepository;
import org.apache.commons.io.FileUtils;
import rs.ac.uns.ftn.XMLProject.Copyright.util.CopyrightRequestDTOMapper;
import rs.ac.uns.ftn.XMLProject.Copyright.util.CopyrightPDFTransformer;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class CopyrightRequestService {

    private final CopyrightSubmissionRequestRepository copyrightSubmissionRequestRepository;
    private final CopyrightPDFTransformer copyrightPDFTransformer = new CopyrightPDFTransformer();

    public CopyrightRequestService(CopyrightSubmissionRequestRepository copyrightSubmissionRequestRepository) {
        this.copyrightSubmissionRequestRepository = copyrightSubmissionRequestRepository;
    }

    public Optional<String> createCopyrightSubmissionRequest(CopyrightSubmissionRequestDTO copyrightSubmissionRequestDTO) {
        List<CopyrightSubmissionRequest> copyrightSubmissionRequests = copyrightSubmissionRequestRepository.findAll();
        int id = 0;

        if(!copyrightSubmissionRequests.isEmpty()) {
            id += copyrightSubmissionRequests.size();
        }

        try {
            CopyrightSubmissionRequest request = CopyrightRequestDTOMapper.copyrightSubmissionRequestFromDTO(copyrightSubmissionRequestDTO);
            request.setRequestNumber(String.format("A-%06d", id));
            request.setRequestSubmissionDate(getXMLGregorianCalendarNow());
            copyrightSubmissionRequestRepository.save(request);
            return Optional.of(request.getRequestNumber());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public CopyrightSubmissionRequestDTO getCopyrightSubmissionRequestById(String id) throws ResourceNotFoundException {
        return CopyrightRequestDTOMapper.copyrightSubmissionRequestToDTO(
                copyrightSubmissionRequestRepository.findById(id).orElseThrow(ResourceNotFoundException::new)
        );
    }

    public List<CopyrightSubmissionRequestDTO> getAllCopyrightSubmissionRequests() {
        return CopyrightRequestDTOMapper.copyrightSubmissionRequestToDTOList(
                copyrightSubmissionRequestRepository.findAll()
        );
    }

    public ByteArrayInputStream getCopyrightSubmissionRequestPDF(String id) throws ResourceNotFoundException {
        ByteArrayInputStream byteArrayInputStream;

        try {

            copyrightPDFTransformer.generateCopyrightRequestHTML(copyrightSubmissionRequestRepository.findById(id)
                    .orElseThrow(ResourceNotFoundException::new));
            copyrightPDFTransformer.generatePDF(id);

            File pdfFile = new File("gen/pdf/" + id + ".pdf");
            File htmlFile = new File("gen/html/" + id + ".html");
            File xmlFile = new File("gen/xml/" + id + ".xml");

            byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(pdfFile));

            pdfFile.delete();
            htmlFile.delete();
            xmlFile.delete();

        } catch (ResourceNotFoundException rnf) {
            throw rnf;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return byteArrayInputStream;
    }

    public String getCopyrightSubmissionRequestHTML(String id) throws ResourceNotFoundException {
        String fileContent = "";

        try {

            copyrightPDFTransformer.generateCopyrightRequestHTML(copyrightSubmissionRequestRepository.findById(id)
                    .orElseThrow(ResourceNotFoundException::new));

            File htmlFile = new File("gen/html/" + id + ".html");
            File xmlFile = new File("gen/xml/" + id + ".xml");
            fileContent = FileUtils.readFileToString(htmlFile, StandardCharsets.UTF_8);
            htmlFile.delete();
            xmlFile.delete();

        } catch (ResourceNotFoundException rnf) {
            throw rnf;
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

    public List<String> getLinkedDocuments(String requestNumber) throws ResourceNotFoundException {

        List<String> linkedDocs = new ArrayList<>();

        CopyrightSubmissionRequest request = copyrightSubmissionRequestRepository.findById(requestNumber)
                .orElseThrow(ResourceNotFoundException::new);

        if (request.isAccepted() != null) {
            linkedDocs.add(request.getRequestNumber() + "-solution");
        }

        copyrightSubmissionRequestRepository.search(requestNumber).stream()
                .forEach((copyrightSubmissionRequest -> {
                    if(!Objects.equals(request.getRequestNumber(), copyrightSubmissionRequest.getRequestNumber())) {
                        linkedDocs.add(copyrightSubmissionRequest.getRequestNumber()+".xml");
                    }
                }));

//        return String.join(",", linkedDocs);
        return linkedDocs;
    }


    public String getRdfMetadata(String requestNumber) {
        return copyrightSubmissionRequestRepository.getRdfMetadata(requestNumber);
    }

    public String getJsonMetadata(String requestNumber) {
        return copyrightSubmissionRequestRepository.getJsonMetadata(requestNumber);
    }

    public List<CopyrightSubmissionRequestDTO> search(String content) {
        return CopyrightRequestDTOMapper.copyrightSubmissionRequestToDTOList(
                copyrightSubmissionRequestRepository.search(content));
    }

    public List<CopyrightSubmissionRequestDTO> searchMetadata(String condition) {
        return CopyrightRequestDTOMapper.copyrightSubmissionRequestToDTOList(
                copyrightSubmissionRequestRepository.searchMetadata(condition));
    }
}
