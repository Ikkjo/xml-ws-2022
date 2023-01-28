package repository;

import models.p.RequestForPatentRecognition;
import org.apache.commons.io.FileUtils;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import util.SparqlUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PatentFusekiOperations {

    private static final String GRAPH_URI = "/patent/metadata";

    public void save(RequestForPatentRecognition requestForPatentRecognition, String xslFile) throws Exception {

        String applicationNumber = requestForPatentRecognition.getInformationForInstitution().getApplicationNumber();
        String rdfFile = "gen/rdf/" + applicationNumber + ".rdf";

        generateRdf(requestForPatentRecognition, xslFile);
        AuthenticationUtilitiesRDF.ConnectionProperties conn = AuthenticationUtilitiesRDF.loadProperties();

        Model model = ModelFactory.createDefaultModel();
        model.read(rdfFile);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, "N-TRIPLES");

        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + GRAPH_URI, out.toString());

        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        processor.execute();

    }

    public void generateRdf(RequestForPatentRecognition requestForPatentRecognition, String xslFile) throws Exception {
        String brojPrijave = requestForPatentRecognition.getInformationForInstitution().getApplicationNumber();
        String rdfFilePath = "gen/rdf/" + brojPrijave + ".rdf";
        String xslFilePath = "data/" + xslFile;

        TransformerFactory factory = TransformerFactory.newInstance();
        InputStream resourceAsStream = FileUtils.openInputStream(new File(xslFilePath));
        StreamSource xslt = new StreamSource(resourceAsStream);
        Transformer transformer = factory.newTransformer(xslt);

        JAXBContext context = JAXBContext.newInstance(RequestForPatentRecognition.class);
        JAXBSource source = new JAXBSource(context, requestForPatentRecognition);
        System.out.println("Source" + source);
        StreamResult result = new StreamResult(Files.newOutputStream(Paths.get(rdfFilePath)));

        transformer.transform(source, result);
    }

    public String getRdfString(String applicationNumber) {

        Model model = ModelFactory.createDefaultModel();
        model.read("gen/rdf/" + applicationNumber + ".rdf");

        String syntax = "RDF/XML-ABBREV";
        StringWriter out = new StringWriter();
        model.write(out, syntax);

        return out.toString();
    }
}
