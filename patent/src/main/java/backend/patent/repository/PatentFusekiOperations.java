package backend.patent.repository;

import backend.patent.model.p.RequestForPatentRecognition;
import backend.patent.util.AuthenticationUtilitiesRDF;
import org.apache.commons.io.FileUtils;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import backend.patent.util.SparqlUtil;

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
        String applicationNumber = requestForPatentRecognition.getInformationForInstitution().getApplicationNumber();
        String rdfFilePath = "gen/rdf/" + applicationNumber + ".rdf";
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

    public long countSubmitted(String startDate, String endDate) throws Exception {

        int res = 0;
        AuthenticationUtilitiesRDF.ConnectionProperties conn = AuthenticationUtilitiesRDF.loadProperties();
        String sparqlQuery = SparqlUtil.selectSubmitted(conn.dataEndpoint + GRAPH_URI, startDate, endDate);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        res = results.getRowNumber();

        query.close();
        return res;

    }

    public long countSubmittedResponded(String startDate, String endDate, String condition) throws Exception {

        int res = 0;
        AuthenticationUtilitiesRDF.ConnectionProperties conn = AuthenticationUtilitiesRDF.loadProperties();
        String sparqlQuery = SparqlUtil.selectSubmittedResponded(conn.dataEndpoint + GRAPH_URI, condition, startDate, endDate);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        res = results.getRowNumber();

        query.close();
        return res;

    }
}
