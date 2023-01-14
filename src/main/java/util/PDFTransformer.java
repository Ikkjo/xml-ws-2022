package util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import models.p.RequestForPatentRecognition;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PDFTransformer {

    private static final DocumentBuilderFactory documentFactory;

    private static final TransformerFactory transformerFactory;

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

        // Initialize Transformer instance
        StreamSource transformSource = new StreamSource(new File("data/P-1.xsl"));
        Transformer transformer = transformerFactory.newTransformer(transformSource);
        transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

        JAXBContext context = JAXBContext.newInstance(RequestForPatentRecognition.class);
        JAXBSource source = new JAXBSource(context, request);

        StreamResult result = new StreamResult(Files.newOutputStream(Paths.get(outputFile)));
        transformer.transform(source, result);

    }
}
