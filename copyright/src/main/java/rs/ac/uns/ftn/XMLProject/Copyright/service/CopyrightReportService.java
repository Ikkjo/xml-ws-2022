package rs.ac.uns.ftn.XMLProject.Copyright.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.XMLProject.Copyright.models.report.CopyrightReport;
import rs.ac.uns.ftn.XMLProject.Copyright.repository.CopyrightRequestSolutionRepository;
import rs.ac.uns.ftn.XMLProject.Copyright.repository.CopyrightSubmissionRequestRepository;
import rs.ac.uns.ftn.XMLProject.Copyright.util.CopyrightPDFTransformer;
import rs.ac.uns.ftn.XMLProject.Copyright.util.CopyrightRequestDTOMapper;
import rs.ac.uns.ftn.XMLProject.Copyright.util.CopyrightSolutionDTOMapper;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
@RequiredArgsConstructor
public class CopyrightReportService {

    private final CopyrightRequestSolutionRepository solutionRepository;
    private final CopyrightSubmissionRequestRepository requestRepository;
    private static final CopyrightRequestDTOMapper requestDTOMapper = new CopyrightRequestDTOMapper();

    private static final CopyrightSolutionDTOMapper solutionDTOMapper = new CopyrightSolutionDTOMapper();
    private static final CopyrightPDFTransformer pdfTransformer = new CopyrightPDFTransformer();
    private final EmailService emailService;

    public ByteArrayInputStream getReportPDF(String startDate, String endDate) {
        ByteArrayInputStream byteArrayInputStream;

        try {

            CopyrightReport report = new CopyrightReport();
            report.setStartDate(stringToXMLDate(startDate));
            report.setEndDate(stringToXMLDate(endDate));
            report.setNumberOfRequests(
                    requestRepository.countSubmitted(startDate, endDate)
            );
            report.setNumberOfAcceptedRequests(
                    solutionRepository.countAccepted(startDate, endDate)
            );
            report.setNumberOfDeclinedRequests(
                    solutionRepository.countDeclined(startDate, endDate)
            );

            pdfTransformer.generateReportHTML(report);

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

    private XMLGregorianCalendar stringToXMLDate(String dateStr) throws ParseException, DatatypeConfigurationException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateStr);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }
}
