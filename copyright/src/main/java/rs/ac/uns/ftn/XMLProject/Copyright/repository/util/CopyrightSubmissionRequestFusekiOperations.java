package rs.ac.uns.ftn.XMLProject.Copyright.repository.util;

import org.apache.jena.query.*;
import rs.ac.uns.ftn.XMLProject.Copyright.models.a.CopyrightSubmissionRequest;
import rs.ac.uns.ftn.XMLProject.Copyright.util.RDFAuthUtils.ConnectionProperties;
import rs.ac.uns.ftn.XMLProject.Copyright.util.RDFAuthUtils;
import rs.ac.uns.ftn.XMLProject.Copyright.util.SparqlUtils;
import org.apache.commons.io.FileUtils;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

        String requestNumber = copyrightSubmissionRequest.getRequestNumber();
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

    public boolean update(CopyrightSubmissionRequest copyrightSubmissionRequest) {
        String requestNumber = copyrightSubmissionRequest.getRequestNumber();
        String rdfFile = "gen/rdf/" + requestNumber + ".rdf";
        try {
            updateRdf(copyrightSubmissionRequest);
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

    private void updateRdf(CopyrightSubmissionRequest copyrightSubmissionRequest) throws Exception {
        String requestNumber = copyrightSubmissionRequest.getRequestNumber();
        String rdfFile = "gen/rdf/" + requestNumber + ".rdf";
        String xslFile = "/data/copyrightMetadata.xsl";

        TransformerFactory factory = TransformerFactory.newInstance();
//        InputStream resourceAsStream = FileUtils.openInputStream(new File(xslFile));
        InputStream resourceAsStream = getClass().getResourceAsStream(xslFile);
        StreamSource xslt = new StreamSource(resourceAsStream);
        Transformer transformer = factory.newTransformer(xslt);

        JAXBContext context = JAXBContext.newInstance(CopyrightSubmissionRequest.class);
        JAXBSource source = new JAXBSource(context, copyrightSubmissionRequest);
        System.out.println("Source" + source);
        StreamResult result = new StreamResult(Files.newOutputStream(Paths.get(rdfFile)));

        transformer.transform(source, result);
    }

    private void generateRdf(CopyrightSubmissionRequest copyrightSubmissionRequest) throws Exception {
        String requestNumber = copyrightSubmissionRequest.getRequestNumber();
        String rdfFile = "gen/rdf/" + requestNumber + ".rdf";
        String xslFile = "/data/copyrightMetadata.xsl";

        TransformerFactory factory = TransformerFactory.newInstance();
//        InputStream resourceAsStream = FileUtils.openInputStream(new File(xslFile));
        InputStream resourceAsStream = getClass().getResourceAsStream(xslFile);
        StreamSource xslt = new StreamSource(resourceAsStream);
        Transformer transformer = factory.newTransformer(xslt);

        JAXBContext context = JAXBContext.newInstance(CopyrightSubmissionRequest.class);
        JAXBSource source = new JAXBSource(context, copyrightSubmissionRequest);
        System.out.println("Source" + source);
        StreamResult result = new StreamResult(Files.newOutputStream(Paths.get(rdfFile)));

        transformer.transform(source, result);
    }

    public String getRdfString(String requestNumber) {

        Model model = ModelFactory.createDefaultModel();
        model.read("gen/rdf/" + requestNumber + ".rdf");

        String syntax = "RDF/XML-ABBREV";
        StringWriter out = new StringWriter();
        model.write(out, syntax);

        return out.toString();
    }

    public String getJsonString(String requestNumber) throws Exception {


        RDFAuthUtils.ConnectionProperties conn = RDFAuthUtils.loadProperties();
        String sparqlQuery = SparqlUtils.selectDataByRequestNumber(conn.dataEndpoint + GRAPH_URI, requestNumber);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ResultSetFormatter.outputAsJSON(outputStream, results);

        return outputStream.toString();

    }

    public BigInteger countSubmitted(String startDate, String endDate) throws Exception {

        int res = 0;
        RDFAuthUtils.ConnectionProperties conn = RDFAuthUtils.loadProperties();
        String sparqlQuery = SparqlUtils.selectSubmitted(conn.dataEndpoint + GRAPH_URI, startDate, endDate);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        while(results.hasNext()) {
            res++;
            results.next();
        }

        query.close();
        return BigInteger.valueOf(res);
    }

    public List<String> rdfQuery(String condition) throws Exception {
        String varName;
        List<String> ids = new ArrayList<>();

        RDFAuthUtils.ConnectionProperties conn = RDFAuthUtils.loadProperties();
        String sparqlQuery = SparqlUtils.selectData(conn.dataEndpoint + GRAPH_URI, condition);
        System.out.println(sparqlQuery);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        while(results.hasNext()) {

            QuerySolution querySolution = results.next() ;
            Iterator<String> variableBindings = querySolution.varNames();

            varName = variableBindings.next();
            ids.add(String.valueOf(querySolution.get(varName).asLiteral()) + ".xml");
        }
        System.out.println(ids);
        return ids;
    }
}
