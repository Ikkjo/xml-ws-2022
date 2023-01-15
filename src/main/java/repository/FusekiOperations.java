package repository;

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

import models.p.RequestForPatentRecognition;
import util.AuthenticationUtilitiesRDF;

public class FusekiOperations {
    public void save(RequestForPatentRecognition requestForPatentRecognition) throws Exception {

        String rdfFile = "gen/rdf/" + requestForPatentRecognition.getInformationForInstitution().getApplicationNumber() + ".rdf";

        TransformerFactory factory = TransformerFactory.newInstance();
        InputStream resourceAsStream = FileUtils.openInputStream(new File("data/metadata.xsl"));
        StreamSource xslt = new StreamSource(resourceAsStream);
        Transformer transformer = factory.newTransformer(xslt);

        JAXBContext context = JAXBContext.newInstance(RequestForPatentRecognition.class);
        JAXBSource source = new JAXBSource(context, requestForPatentRecognition);
        System.out.println("Source" + source);
        StreamResult result = new StreamResult(Files.newOutputStream(Paths.get(rdfFile)));

        transformer.transform(source, result);

        AuthenticationUtilitiesRDF.ConnectionProperties conn = AuthenticationUtilitiesRDF.loadProperties();

        Model model = ModelFactory.createDefaultModel();
        String applicationNumber = requestForPatentRecognition.getInformationForInstitution().getApplicationNumber();
        model.read(rdfFile);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, "N-TRIPLES");

        String sparqlUpdate = String.format("INSERT DATA { GRAPH <%1$s> { %2$s } }", conn.dataEndpoint + "/patent/metadata", out.toString());

        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        processor.execute();

    }
}
