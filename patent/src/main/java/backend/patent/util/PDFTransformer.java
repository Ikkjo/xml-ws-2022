package backend.patent.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import backend.patent.model.p.RequestForPatentRecognition;
import backend.patent.model.report.Report;
import backend.patent.model.solution.PatentSolution;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PDFTransformer {

    private static final DocumentBuilderFactory documentFactory;

    private static final TransformerFactory transformerFactory;

    private static final String XSL_REQUEST_FILEPATH = "patent/src/main/java/backend/patent/data/p-1.xsl";
    private static final String XSL_SOLUTION_FILEPATH = "data/Solution.xsl";
    private static final String XSL_REPORT_FILEPATH = "data/Report.xsl";

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

        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(outputFile)));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, Files.newInputStream(Paths.get(inputFile)));
        document.close();

    }

    public void generateHTMLRequest(RequestForPatentRecognition request) throws Exception {
        String htmlFile = "gen/html/" + request.getInformationForInstitution().getApplicationNumber() + ".html";
        String xmlFile = "gen/xml/" + request.getInformationForInstitution().getApplicationNumber() + ".xml";

        // initializa xml file source
        writeToXMLFile(request, xmlFile);

        generateHTML(xmlFile, htmlFile, XSL_REQUEST_FILEPATH);
    }

    public void generateHTMLSolution(PatentSolution solution) throws Exception {
        String htmlFile = "gen/html/" + solution.getApplicationNumber() + ".html";
        String xmlFile = "gen/xml/" + solution.getApplicationNumber() + ".xml";

        // initializa xml file source
        writeToXMLFile(solution, xmlFile);

        generateHTML(xmlFile, htmlFile, XSL_SOLUTION_FILEPATH);
    }

    public void generateHTMLReport(Report report) throws Exception {

        String htmlFile = "gen/html/report.html";
        String xmlFile = "gen/xml/report.xml";

        // initializa xml file source
        writeToXMLFile(report);

        generateHTML(xmlFile, htmlFile, XSL_REPORT_FILEPATH);

    }

    public void generateHTML(String xmlFile, String htmlFile, String xslPath) throws Exception {
        // Initialize Transformer instance
        StreamSource transformSource = new StreamSource(new File(xslPath));
        Transformer transformer = transformerFactory.newTransformer(transformSource);
        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // Generate XHTML
        transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

        // Transform DOM to HTML
        DOMSource source = new DOMSource(buildDocument(xmlFile));
        StreamResult result = new StreamResult(Files.newOutputStream(Paths.get(htmlFile)));
        transformer.transform(source, result);

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

    private void writeToXMLFile(RequestForPatentRecognition request, String filename) throws JAXBException, IOException {

        JAXBContext context = JAXBContext.newInstance("backend.patent.model.p");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(request, Files.newOutputStream(Paths.get(filename)));

    }

    private void writeToXMLFile(PatentSolution solution, String filename) throws JAXBException, IOException {

        JAXBContext context = JAXBContext.newInstance("backend.patent.model.solution");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(solution, Files.newOutputStream(Paths.get(filename)));

    }

    private void writeToXMLFile(Report report) throws Exception {

        JAXBContext context = JAXBContext.newInstance("backend.patent.model.report");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(report, Files.newOutputStream(Paths.get("gen/xml/report.xml")));

    }
}
