package backend.patent.service;

import backend.patent.model.p.RequestForPatentRecognition;
import backend.patent.model.p.dto.CreatePatentRecognitionRequestDTO;
import backend.patent.model.p.dto.RequestForPatentRecognitionDTO;
import org.springframework.stereotype.Service;
import backend.patent.repository.RequestForPatentRecognitionRepository;
import backend.patent.util.PDFTransformer;
import org.apache.commons.io.FileUtils;
import backend.patent.util.PatentDTOMapper;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class PatentService {

    private final RequestForPatentRecognitionRepository repository;
    private final PDFTransformer pdfTransformer;
    private final PatentDTOMapper dtoUtils;

    public PatentService(RequestForPatentRecognitionRepository repository, PDFTransformer pdfTransformer, PatentDTOMapper dtoUtils) {
        this.repository = repository;
        this.pdfTransformer = pdfTransformer;
        this.dtoUtils = dtoUtils;
    }

    public ByteArrayInputStream getRequestForPatentRecognitionPDF(String id) {
        ByteArrayInputStream byteArrayInputStream;

        try {

            pdfTransformer.generateHTMLRequest(repository.findById(id));
            pdfTransformer.generatePDF(id);

            File pdfFile = new File("gen/pdf/" + id + ".pdf");
            File htmlFile = new File("gen/html/" + id + ".html");
            File xmlFile = new File("gen/xml/" + id + ".xml");
            byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(pdfFile));
            pdfFile.delete();
            htmlFile.delete();
            xmlFile.delete();

        } catch (Exception e) {
            return null;
        }

        return byteArrayInputStream;
    }

    public String getRequestForPatentRecognitionHTML(String id) {
        String fileContent = "";

        try {

            pdfTransformer.generateHTMLRequest(repository.findById(id));

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

    public Optional<RequestForPatentRecognitionDTO> getPatentRecognitionRequest(String id) throws Exception {
        RequestForPatentRecognition request = repository.findById(id);

        if (request != null) {
            return Optional.of(PatentDTOMapper.patentRecognitionRequestToDTO(request));
        } else {
            return Optional.empty();
        }
    }

    public String createNewPatentRecognitionRequest(CreatePatentRecognitionRequestDTO createPatentRecognitionRequestDTO) throws Exception {
        List<RequestForPatentRecognition> requests = repository.getAll();

        int id = 0;

        if(!requests.isEmpty()) {
            id += requests.size();
        }

        RequestForPatentRecognition request = dtoUtils.PatentRecognitionRequestFromDTO(createPatentRecognitionRequestDTO);
        request.getInformationForInstitution().setApplicationNumber(String.format("P-%06d", id));
        request.getInformationForInstitution().setSubmissionDate(getCurrentDate());
        repository.save(request);
        return request.getInformationForInstitution().getApplicationNumber();
    }

    private XMLGregorianCalendar getCurrentDate() throws DatatypeConfigurationException {
        Date now = new Date();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(now);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

    }

    public Optional<String> getRdfMetadata(String id) {
        try {
            String rdfString = repository.createRdfString(id);
            deleteFile("gen/rdf/" + id + ".rdf");
            return Optional.of(rdfString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

    public Optional<String> getJsonMetadata(String id) {

        try {
            return Optional.of(repository.getJsonString(id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<RequestForPatentRecognitionDTO> searchByContent(String role, String content) {
        return filterByRole(role, repository.searchByContent(content));
    }

    private List<RequestForPatentRecognitionDTO> filterByRole(String role, ArrayList<RequestForPatentRecognition> requests) {
        if (Objects.equals(role, "Sluzbenik")) {
            return dtoUtils.patentRecognitionRequestsToDTOList(requests);
        }
        else {
            ArrayList<RequestForPatentRecognition> filtered = new ArrayList<>();
            for (RequestForPatentRecognition request : requests)
                if (request.getIsAccepted() != null)
                    filtered.add(request);
            return dtoUtils.patentRecognitionRequestsToDTOList(filtered);
        }
    }

    public List<RequestForPatentRecognitionDTO> search(String role, String condition) {
        return filterByRole(role, repository.search(condition));
    }

    public String getLinkedDocuments(String id) {
        try {
            ArrayList<String> idList = new ArrayList<>();
            RequestForPatentRecognition request = repository.findById(id);
            List<RequestForPatentRecognition> requests = repository.searchByContent(id);
            if (request.getIsAccepted() != null)
                idList.add(request.getInformationForInstitution().getApplicationNumber());
            for (RequestForPatentRecognition singleRequest: requests) {
                if (!Objects.equals(singleRequest.getInformationForInstitution().getApplicationNumber(), request.getInformationForInstitution().getApplicationNumber()))
                    idList.add(singleRequest.getInformationForInstitution().getApplicationNumber());
            }
            return String.join(",", idList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
