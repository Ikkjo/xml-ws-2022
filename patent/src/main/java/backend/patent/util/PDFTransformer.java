package backend.patent.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import backend.patent.model.p.RequestForPatentRecognition;
import backend.patent.model.report.Report;
import backend.patent.model.solution.PatentSolution;


@Component
public class PDFTransformer {

    private static final DocumentBuilderFactory documentFactory;

    private static final TransformerFactory transformerFactory;

    private static final String XSL_REQUEST_FILEPATH = "/data/p-1.xsl";
    private static final String XSL_SOLUTION_FILEPATH = "/data/Solution.xsl";
    private static final String XSL_REPORT_FILEPATH = "/data/Report.xsl";

    public static final String HTML_PATH = "gen/html/";

    public static final String PDF_PATH = "gen/pdf/";

    public static final String XML_PATH = "gen/xml/";

    static {

        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        transformerFactory = TransformerFactory.newInstance();

    }

    public void generatePDF(String id) throws IOException, DocumentException {

        String inputFile = "gen/html/" + id + ".html";
        String outputFile = "gen/pdf/" + id + ".pdf";

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputFile));
        pdfDocument.setDefaultPageSize(new PageSize(595,842));

        HtmlConverter.convertToPdf(Files.newInputStream(Paths.get(inputFile)), pdfDocument);

    }

    public org.w3c.dom.Document buildDocument(String filePath) {

        org.w3c.dom.Document document = null;
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(new File(filePath));

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            return null;

        }

        return document;
    }

    public void generateHTMLRequest(RequestForPatentRecognition request) throws Exception {
        String htmlFile = HTML_PATH + request.getInformationForInstitution().getApplicationNumber() + ".html";
        String xmlFile = XML_PATH + request.getInformationForInstitution().getApplicationNumber() + ".xml";

        // initializa xml file source
        writeToXMLFile(request, xmlFile);

        generateHTML(xmlFile, htmlFile, XSL_REQUEST_FILEPATH);
    }

    public void generateHTMLSolution(PatentSolution solution) throws Exception {
        String htmlFile = HTML_PATH + solution.getApplicationNumber() + "-solution.html";
        String xmlFile = XML_PATH + solution.getApplicationNumber() + "-solution.xml";

        // initializa xml file source
        writeToXMLFile(solution, xmlFile);

        generateHTML(xmlFile, htmlFile, XSL_SOLUTION_FILEPATH);
    }

    public void generateHTMLReport(Report report) throws Exception {

        String htmlFile = HTML_PATH + report.getStartDate() + "-" + report.getEndDate() + "-report.html";
        String xmlFile = XML_PATH + report.getStartDate() + "-" + report.getEndDate() + "-report.xml";

        // initializa xml file source
        writeToXMLFile(report, xmlFile);

        generateHTML(xmlFile, htmlFile, XSL_REPORT_FILEPATH);

    }

    public void generateHTML(String xmlFile, String htmlFile, String xslPath) throws Exception {

        try {
            // Initialize Transformer instance
            InputStream resourceAsStream = getClass().getResourceAsStream(xslPath);
            StreamSource xslt = new StreamSource(resourceAsStream);
            Transformer transformer = transformerFactory.newTransformer(xslt);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            DOMSource source = new DOMSource(buildDocument(xmlFile));
            StreamResult result = new StreamResult(Files.newOutputStream(Paths.get(htmlFile)));
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    private void writeToXMLFile(RequestForPatentRecognition request, String filename) throws JAXBException, IOException {

        JAXBContext context = JAXBContext.newInstance(RequestForPatentRecognition.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(request, Files.newOutputStream(Paths.get(filename)));

    }

    private void writeToXMLFile(PatentSolution solution, String filename) throws JAXBException, IOException {

        JAXBContext context = JAXBContext.newInstance(PatentSolution.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(solution, Files.newOutputStream(Paths.get(filename)));

    }

    private void writeToXMLFile(Report report) throws Exception {

        JAXBContext context = JAXBContext.newInstance(Report.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(report, Files.newOutputStream(Paths.get(filename)));

    }
}
