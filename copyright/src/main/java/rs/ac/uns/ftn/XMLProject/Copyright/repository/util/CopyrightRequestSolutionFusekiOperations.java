package rs.ac.uns.ftn.XMLProject.Copyright.repository.util;

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
import rs.ac.uns.ftn.XMLProject.Copyright.models.solution.CopyrightRequestSolution;
import rs.ac.uns.ftn.XMLProject.Copyright.util.RDFAuthUtils;
import rs.ac.uns.ftn.XMLProject.Copyright.util.SparqlUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CopyrightRequestSolutionFusekiOperations {
    private static final String GRAPH_URI = "/copyright/metadata";

    private RDFAuthUtils.ConnectionProperties conn;

    public CopyrightRequestSolutionFusekiOperations() {
        try {
            conn = RDFAuthUtils.loadProperties();
        } catch (IOException e) {
            System.out.println("Couldn't read Fuseki RDF properties");
        }
    }

    public boolean save(CopyrightRequestSolution copyrightRequestSolution) {

        String requestNumber = copyrightRequestSolution.getRequestNumber();
        String rdfFile = "gen/rdf/" + requestNumber + "-solution.rdf";
        try {
            generateRdf(copyrightRequestSolution);
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

    private void generateRdf(CopyrightRequestSolution copyrightRequestSolution) throws Exception {
        String requestNumber = copyrightRequestSolution.getRequestNumber();
        String rdfFile = "gen/rdf/" + requestNumber + "-solution.rdf";
        String xslFile = "/data/copyrightMetadata.xsl";

        TransformerFactory factory = TransformerFactory.newInstance();
//        InputStream resourceAsStream = FileUtils.openInputStream(new File(xslFile));
        InputStream resourceAsStream = getClass().getResourceAsStream(xslFile);
        StreamSource xslt = new StreamSource(resourceAsStream);
        Transformer transformer = factory.newTransformer(xslt);

        JAXBContext context = JAXBContext.newInstance(CopyrightRequestSolution.class);
        JAXBSource source = new JAXBSource(context, copyrightRequestSolution);
        System.out.println("Source" + source);
        StreamResult result = new StreamResult(Files.newOutputStream(Paths.get(rdfFile)));

        transformer.transform(source, result);
    }

    public BigInteger countAccepted(String startDate, String endDate) throws IOException {
        return BigInteger.valueOf(countSolutions(startDate, endDate, true));
    }

    public BigInteger countDeclined(String startDate, String endDate) throws IOException {
        return BigInteger.valueOf(countSolutions(startDate, endDate, false));
    }

    private long countSolutions(String startDate, String endDate, Boolean accepted) throws IOException {
        long res = 0;
        RDFAuthUtils.ConnectionProperties conn = RDFAuthUtils.loadProperties();
        String sparqlQuery = SparqlUtils.selectSolutions(conn.dataEndpoint + GRAPH_URI, accepted.toString(), startDate, endDate);
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        while(results.hasNext()) {
            res++;
            results.next();
        }

        query.close();

        return res;
    }
}
