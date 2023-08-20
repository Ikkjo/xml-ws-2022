package rs.ac.uns.ftn.XMLProject.Copyright.util;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SparqlUtils {

    /* The following operation causes all the triples in all the graphs to be deleted */
    private static final String DROP_ALL = "DROP ALL";

    /* Removes all the triples from a named graphed */
    private static final String DROP_GRAPH_TEMPLATE = "DROP GRAPH <%s>";

    /**
     * A template for creating SPARUL (SPARQL Update) query can be found here:
     * https://www.w3.org/TR/sparql11-update/
     */
    /* Insert RDF data into the default graph */
    private static final String UPDATE_TEMPLATE = "INSERT DATA { %s }";

    /* Insert RDF data to an arbitrary named graph */
    private static final String UPDATE_TEMPLATE_NAMED_GRAPH = "INSERT DATA { GRAPH <%1$s> { %2$s } }";


    /* Simple SPARQL query on a named graph */
    private static final String SELECT_NAMED_GRAPH_TEMPLATE =
            "  SELECT ?request_number \n" +
                    "  FROM <%1$s> WHERE { \n" +
                    "  %2$s " +
                    "  FILTER( %3$s )" +
                    "  }";


    private static final String SELECT_BY_REQUEST_NUMBER =
            "   SELECT ?request_number ?request_submission_date ?Work_title ?Work_type ?Applicant\n" +
                    "FROM <%1$s> WHERE { \n" +
                    "  ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1request_number> ?request_number .\n" +
                    "  ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1request_submission_date> ?request_submission_date .\n" +
                    "  ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1Work_title> ?title .\n" +
                    "  ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1Work_type> ?work_type .\n" +
                    "  ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1Applicant> ?applicant .\n" +
                    "  FILTER( ?request_number = \"%2$s\" )" +
                    "  }";

    private static final String SELECT_SUBMISSION =
            "   PREFIX a1: <http://www.XMLproject.ftn.uns.ac.rs/a-1>" +
                    "SELECT ?request_number \n" +
                    "FROM <%1$s> WHERE { \n" +
                    "  ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1request_number> ?request_number .\n" +
                    "  ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1request_submission_date> ?request_submission_date .\n" +
                    "  FILTER( \"%2$s\" < ?request_submission_date && ?request_submission_date < \"%3$s\" )\n" +
                    "  }";

    private static final String SELECT_RESPONDED_SUBMISSION =
            "   PREFIX a1: <http://www.XMLproject.ftn.uns.ac.rs/a-1>" +
                    "SELECT ?request_number \n" +
                    "FROM <%1$s> WHERE { \n" +
                    "  ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1request_number> ?request_number .\n" +
                    "  ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1is_accepted> ?is_accepted .\n" +
                    "  ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1request_submission_date> ?request_submission_date .\n" +
                    "  FILTER( ?is_accepted = \"%2$s\" && \"%3$s\" < ?request_submission_date && ?request_submission_date < \"%4$s\" )\n" +
                    "  }";

    /* Plain text RDF serialization format */
    public static final String NTRIPLES = "N-TRIPLES";

    /* An XML serialization format for RDF data */
    public static final String RDF_XML = "RDF/XML";

    public static String dropAll() {
        return DROP_ALL;
    }

    public static String dropGraph(String graphURI) {
        return String.format(DROP_GRAPH_TEMPLATE, graphURI);
    }

    /* Inserts data to the default graph */
    public static String insertData(String ntriples) {
        return String.format(UPDATE_TEMPLATE, ntriples);
    }

    public static String insertData(String graphURI, String ntriples) {
        return String.format(UPDATE_TEMPLATE_NAMED_GRAPH, graphURI, ntriples);
    }

    public static String selectData(String graphURI, String sparqlCondition) {
        String selector = getSelectorFromCondition(sparqlCondition);
        sparqlCondition = getQueryPredicateFromCondition(sparqlCondition);
//        System.out.println(sparqlCondition);
        sparqlCondition = operatorMapping(sparqlCondition);
//        System.out.println(sparqlCondition);
        return String.format(SELECT_NAMED_GRAPH_TEMPLATE, graphURI, selector, sparqlCondition);
    }

    public static String selectDataByRequestNumber(String graphUri, String requestNumber) {
        return String.format(SELECT_BY_REQUEST_NUMBER, graphUri, requestNumber);
    }

    public static String selectSubmitted(String graphUri, String startDate, String endDate) {
        return String.format(SELECT_SUBMISSION, graphUri, startDate, endDate);
    }

    public static String selectSolutions(String graphUri, String accepted, String startDate, String endDate) {
        return String.format(SELECT_RESPONDED_SUBMISSION, graphUri, accepted, startDate, endDate);
    }

    private static final Map<String, String> predicateSelectorMap = Stream.of(new String[][] {
            { "title", " ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1title> ?title .\n" },
            { "applicant", " ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1applicant> ?applicant .\n" },
            { "is_accepted", " ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1is_accepted> ?is_accepted .\n" },
            { "request_submission_date", "?request <http://www.XMLproject.ftn.uns.ac.rs/a-1request_submission_date> ?request_submission_date .\n" },
            { "work_type", " ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1work_type> ?work_type .\n"},
            { "request_number", " ?request <http://www.XMLproject.ftn.uns.ac.rs/a-1request_number> ?request_number .\n"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    private static final Map<String, String> predicateMap = Stream.of(new String[][] {
            { "request_number", "?request_number" },
            { "request_submission_date", "?request_submission_date" },
            { "title", "?title" },
            { "work_type", "?work_type" },
            { "applicant", "?applicant" },
            { "is_accepted", "?is_accepted = \"true\"" },
            { "not_accepted", "?is_accepted = \"false\"" },
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    private static String getQueryPredicateFromCondition(String condition) {
        for (Map.Entry<String, String> entry: predicateMap.entrySet()) {
            condition = condition.replace(entry.getKey(), entry.getValue());
        }
        return condition;
    }

    private static String getSelectorFromCondition(String condition) {
        StringBuilder selector = new StringBuilder(predicateSelectorMap.get("request_number"));
        for (Map.Entry<String, String> set : predicateSelectorMap.entrySet()) {
            if (condition.contains(set.getKey())) {
                selector.append(set.getValue());
            }
        }
        return selector.toString();
    }

    private static String operatorMapping(String condition) {
        condition = condition.replace(" and ", " && ");
        condition = condition.replace(" or ", " || ");
        condition = condition.replace(" neq ", " != ");
        condition = condition.replace(" before ", " < ");
        condition = condition.replace(" after ", " > ");
        condition = condition.replace(" is ", " = ");
        condition = condition.replace(" not ", " ! ");
        return condition;
    }
}