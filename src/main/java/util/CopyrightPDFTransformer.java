package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import models.a.CopyrightSubmissionRequest;

public class CopyrightPDFTransformer {
    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    public static final String A1_XSL_FILE = "data/A-1.xsl";

    public static final String HTML_PATH = "gen/html/";

    public static final String PDF_PATH = "gen/pdf/";

    public static final String XML_PATH = "gen/xml/";

    static {

        /* Inicijalizacija DOM fabrike */
        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        /* Inicijalizacija Transformer fabrike */
        transformerFactory = TransformerFactory.newInstance();

    }

    /**
     * Creates a PDF using iText Java API
     * @param id
     * @throws IOException
     * @throws DocumentException
     */
    public void generatePDF(String id) throws IOException, DocumentException {

        String inputFile = "gen/html/" + id + ".html";
        String outputFile = "gen/pdf/" + id + ".pdf";

        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(outputFile));
        pdfDocument.setDefaultPageSize(new PageSize(780,2000));
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

    public void generateCopyrightRequestHTML(CopyrightSubmissionRequest copyrightSubmissionRequest) throws JAXBException, IOException {
        String xmlFilename = XML_PATH + copyrightSubmissionRequest.getRequestNumber() + ".xml";
        String htmlFilename = HTML_PATH + copyrightSubmissionRequest.getRequestNumber() + ".html";

        writeToXMLFile(copyrightSubmissionRequest, xmlFilename);

        generateHTML(xmlFilename, A1_XSL_FILE, htmlFilename);
    }

    public void generateHTML(String xmlPath, String xslPath, String outputHtmlPath) throws FileNotFoundException {

        try {

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            DOMSource source = new DOMSource(buildDocument(xmlPath));
            StreamResult result = new StreamResult(new FileOutputStream(outputHtmlPath));
            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    private void writeToXMLFile(CopyrightSubmissionRequest copyrightSubmissionRequest, String filename)
            throws JAXBException, IOException {

        JAXBContext context = JAXBContext.newInstance("models.a");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(copyrightSubmissionRequest, Files.newOutputStream(Paths.get(filename)));

    }
}