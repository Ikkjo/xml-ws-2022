package backend.patent.service;

import backend.patent.model.p.RequestForPatentRecognition;
import backend.patent.model.p.dto.CreatePatentRecognitionRequestDTO;
import backend.patent.model.p.dto.RequestForPatentRecognitionDTO;
import org.apache.regexp.RE;
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

    private RequestForPatentRecognitionRepository repository = new RequestForPatentRecognitionRepository();
    private PDFTransformer pdfTransformer = new PDFTransformer();
    private PatentDTOMapper dtoUtils = new PatentDTOMapper();

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
            throw new RuntimeException(e);
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

    public RequestForPatentRecognitionDTO getPatentRecognitionRequest(String id) throws Exception {
        return dtoUtils.patentRecognitionRequestToDTO(repository.findById(id));
    }

    public void createNewPatentRecognitionRequest(CreatePatentRecognitionRequestDTO createPatentRecognitionRequestDTO) throws Exception {
        List<RequestForPatentRecognition> requests = repository.getAll();
        int maxId = -1;
        int id;
        for (RequestForPatentRecognition request : requests) {
            id = Integer.parseInt(request.getInformationForInstitution().getApplicationNumber());
            if (id > maxId){
                maxId = id;
            }
        }
        maxId++;
        RequestForPatentRecognition request = dtoUtils.PatentRecognitionRequestFromDTO(createPatentRecognitionRequestDTO);
        request.getInformationForInstitution().setApplicationNumber(String.format("P-%06d", maxId));
        request.getInformationForInstitution().setSubmissionDate(getCurrentDate());
        repository.save(request);
    }

    private XMLGregorianCalendar getCurrentDate() throws DatatypeConfigurationException {
        Date now = new Date();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(now);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

    }

    public String getRdfMetadata(String id) {
        try {
            String rdfString = repository.createRdfString(id);
            deleteFile("gen/rdf/" + id + ".rdf");
            return rdfString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

    public String getJsonMetadata(String id) {

        try {
            return repository.getJsonString(id);
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
}
