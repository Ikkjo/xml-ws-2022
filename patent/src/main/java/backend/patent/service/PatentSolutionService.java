package service;

import models.p.RequestForPatentRecognition;
import models.solution.PatentSolution;
import models.solution.dto.PatentSolutionDTO;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import repository.PatentSolutionRepository;
import repository.RequestForPatentRecognitionRepository;
import util.PDFTransformer;
import util.PatentSolutionDTOMapper;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class PatentSolutionService {

    private final PatentSolutionRepository solutionRepository = new PatentSolutionRepository();
    private final RequestForPatentRecognitionRepository requestRepository = new RequestForPatentRecognitionRepository();
    private final PatentSolutionDTOMapper dtoMapper = new PatentSolutionDTOMapper();
    private final PDFTransformer pdfTransformer = new PDFTransformer();

    public String getSolutionHTML(String id) {
        String fileContent = "";

        try {
            pdfTransformer.generateHTMLSolution(solutionRepository.findById(id));

            File htmlFile = new File("gen/html" + id + ".html");
            fileContent = FileUtils.readFileToString(htmlFile, StandardCharsets.UTF_8);
            deleteFile("gen/html/" + id + ".html");
            deleteFile("gen/xml/" + id + ".xml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fileContent;
    }

    public void createPatentSolution(PatentSolutionDTO solutionDTO) throws Exception {
        PatentSolution solution = dtoMapper.convertSolutionFromDTO(solutionDTO);
        RequestForPatentRecognition request = requestRepository.findById(solutionDTO.applicationNumber);
        request.setIsAccepted(solutionDTO.isAccepted);
        request.getInformationForInstitution().setReceiptDate(solution.getAcceptedOrRejectedDate());
        requestRepository.update(request);
        solutionRepository.save(solution);

        deleteFile("gen/rdf/" + request.getInformationForInstitution().getApplicationNumber() + ".rdf");
    }

    private void deleteFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

    private XMLGregorianCalendar convertStringToDate(String str) throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(str);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);

        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }
}
