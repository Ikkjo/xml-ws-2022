package util;

import models.a.CopyrightSubmissionRequest;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class DocumentTransformer {
    private static final DocumentBuilderFactory documentFactory;
    private static final TransformerFactory transformerFactory;
    private static final String XSL_FILEPATH = "data/a1.xsl";


    static {

        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        transformerFactory = TransformerFactory.newInstance();

    }

    public static void generatePDF(String id) throws IOException, DocumentException {

        String inputFile = "gen/html/" + id + ".html";
        String outputFile = "gen/pdf/" + id + ".pdf";

        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, Files.newOutputStream(Paths.get(outputFile)));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, Files.newInputStream(Paths.get(inputFile)));
        document.close();

    }

    public static void generateXHTML(CopyrightSubmissionRequest request) throws Exception {

        String outputFile = "gen/html/" + request.getInformationForInstitution().getRequestNumber() + ".html";
        String xmlFile = "gen/xml/" + request.getInformationForInstitution().getRequestNumber() + ".xml";

        // initializa xml file source
        writeToXMLFile(request, xmlFile);
        // Initialize Transformer instance
        StreamSource transformSource = new StreamSource(new File(XSL_FILEPATH));
        Transformer transformer = transformerFactory.newTransformer(transformSource);
        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

        // Transform DOM to HTML
        DOMSource source = new DOMSource(buildDocument(xmlFile));
        StreamResult result = new StreamResult(Files.newOutputStream(Paths.get(outputFile)));
        transformer.transform(source, result);

    }


    public static org.w3c.dom.Document buildDocument(String filePath) {

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

    private static void writeToXMLFile(CopyrightSubmissionRequest request, String filename) throws JAXBException, IOException {

        JAXBContext context = JAXBContext.newInstance("model.a");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(request, Files.newOutputStream(Paths.get(filename)));

    }
}
