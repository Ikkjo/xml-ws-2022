package backend.patent.service;

import backend.patent.model.p.RequestForPatentRecognition;
import backend.patent.model.report.Report;
import backend.patent.model.solution.PatentSolution;
import backend.patent.model.solution.dto.PatentSolutionDTO;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import backend.patent.repository.PatentSolutionRepository;
import backend.patent.repository.RequestForPatentRecognitionRepository;
import backend.patent.util.PDFTransformer;
import backend.patent.util.PatentSolutionDTOMapper;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class PatentSolutionService {

    private final PatentSolutionRepository solutionRepository;
    private final RequestForPatentRecognitionRepository requestRepository;
    private final PatentSolutionDTOMapper dtoMapper;
    private final PDFTransformer pdfTransformer;

    public PatentSolutionService(PatentSolutionRepository solutionRepository, RequestForPatentRecognitionRepository requestRepository,
                                 PatentSolutionDTOMapper dtoMapper, PDFTransformer pdfTransformer) {
        this.solutionRepository = solutionRepository;
        this.requestRepository = requestRepository;
        this.dtoMapper = dtoMapper;
        this.pdfTransformer = pdfTransformer;
    }

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

    public String createPatentSolution(PatentSolutionDTO solutionDTO) throws Exception {
        PatentSolution solution = dtoMapper.convertSolutionFromDTO(solutionDTO);
        RequestForPatentRecognition request = requestRepository.findById(solutionDTO.applicationNumber);
        request.setIsAccepted(solutionDTO.isAccepted);
        request.getInformationForInstitution().setReceiptDate(solution.getAcceptedOrRejectedDate());
        requestRepository.update(request);
        solutionRepository.save(solution);

        deleteFile("gen/rdf/" + request.getInformationForInstitution().getApplicationNumber() + ".rdf");

        return request.getApplicationInformation().getOriginalApplicationNumber();
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

    public ByteArrayInputStream getReportPDF(String startDate, String endDate) {
        ByteArrayInputStream byteArrayInputStream;

        try {
            Report report = new Report();

            report.setStartDate(convertStringToDate(startDate));
            report.setEndDate(convertStringToDate(endDate));
            report.setSubmittedRequestsNumber(solutionRepository.countSubmitted(startDate, endDate));
            report.setAcceptedRequestsNumber(solutionRepository.countSubmittedResponded(startDate,endDate, "true"));
            report.setRejectedRequestsNumber(solutionRepository.countSubmittedResponded(startDate, endDate, "false"));

            pdfTransformer.generateHTMLReport(report);

            String reportFile = report.getStartDate() + "-" + report.getEndDate() + "-report";

            pdfTransformer.generatePDF(reportFile);

            File pdfFile = new File("gen/pdf/" + reportFile + ".pdf");
            File xmlFile = new File("gen/xml/" + reportFile + ".xml");
            File htmlFile = new File("gen/html/" + reportFile + ".html");
            byteArrayInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(pdfFile));
            pdfFile.delete();
            xmlFile.delete();
            htmlFile.delete();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return byteArrayInputStream;
    }
}
