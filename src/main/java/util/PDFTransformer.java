package util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import models.p.RequestForPatentRecognition;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
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

    private static final String XSL_FILEPATH = "data/p-1.xsl";

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

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(outputFile)));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, Files.newInputStream(Paths.get(inputFile)));
        document.close();

    }

    public void generateHTML(RequestForPatentRecognition request) throws Exception {

        String outputFile = "gen/html/" + request.getInformationForInstitution().getApplicationNumber() + ".html";
        String xmlFile = "gen/xml/" + request.getInformationForInstitution().getApplicationNumber() + ".xml";

        // initializa xml file source
        writeToXMLFile(request, xmlFile);

        // Initialize Transformer instance
        StreamSource transformSource = new StreamSource(new File(XSL_FILEPATH));
        Transformer transformer = transformerFactory.newTransformer(transformSource);
        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // Generate XHTML
        transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

        // Transform DOM to HTML
        DOMSource source = new DOMSource(buildDocument(xmlFile));
        StreamResult result = new StreamResult(new FileOutputStream(outputFile));
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

        JAXBContext context = JAXBContext.newInstance("backend.patent.jaxb");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(request, Files.newOutputStream(Paths.get(filename)));

    }
}
