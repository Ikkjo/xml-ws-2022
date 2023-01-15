package repository;

import models.a.CopyrightSubmissionRequest;
import util.RDFAuthUtils.ConnectionProperties;
import util.RDFAuthUtils;
import util.SparqlUtils;
import org.apache.commons.io.FileUtils;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CopyrightSubmissionRequestFusekiOperations {
    private static final String GRAPH_URI = "/copyright/metadata";

    private ConnectionProperties conn;

    public CopyrightSubmissionRequestFusekiOperations() {
        try {
            conn = RDFAuthUtils.loadProperties();
        } catch (IOException e) {
            System.out.println("Couldn't read Fuseki RDF properties");
        }
    }

    public boolean save(CopyrightSubmissionRequest copyrightSubmissionRequest) {

        String requestNumber = copyrightSubmissionRequest.getInformationForInstitution().getRequestNumber();
        String rdfFile = "gen/rdf/" + requestNumber + ".rdf";
        try {
            generateRdf(copyrightSubmissionRequest);
        } catch (Exception e) {
            return false;
        }


        Model model = ModelFactory.createDefaultModel();
        model.read(rdfFile);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, "N-TRIPLES");

        String sparqlUpdate = SparqlUtils.insertData(conn.dataEndpoint + GRAPH_URI, out.toString());
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        processor.execute();

        return true;

    }

    private void generateRdf(CopyrightSubmissionRequest copyrightSubmissionRequest) throws Exception {
        String requestNumber = copyrightSubmissionRequest.getInformationForInstitution().getRequestNumber();
        String rdfFile = "gen/rdf/" + requestNumber + ".rdf";
        String xslFile = "data/metadata.xsl";

        TransformerFactory factory = TransformerFactory.newInstance();
        InputStream resourceAsStream = FileUtils.openInputStream(new File(xslFile));
        StreamSource xslt = new StreamSource(resourceAsStream);
        Transformer transformer = factory.newTransformer(xslt);

        JAXBContext context = JAXBContext.newInstance(CopyrightSubmissionRequest.class);
        JAXBSource source = new JAXBSource(context, copyrightSubmissionRequest);
        System.out.println("Source" + source);
        StreamResult result = new StreamResult(Files.newOutputStream(Paths.get(rdfFile)));

        transformer.transform(source, result);
    }
}
