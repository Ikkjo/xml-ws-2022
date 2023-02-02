package backend.patent.util;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SparqlUtil {

    /**
     * A template for creating SPARUL (SPARQL Update) query can be found here:
     * https://www.w3.org/TR/sparql11-update/
     */

    private static final Map<String, String> predicateToSelector = Stream.of(new String[][] {
            { "Applicant", " ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionApplicant> ?Applicant . \n" },
            { "Receipt_date", " ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionReceipt_date> ?Receipt_date . \n" },
            { "Serbian_patent_name", " ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionSerbian_patent_name> ?Serbian_patent_name . \n" },
            { "English_patent_name", " ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionEnglish_patent_name> ?English_patent_name . \n" },
            { "is_accepted", " ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionis_accepted> ?is_accepted . \n" },
            { "Submission_date", " ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionSubmission_date> ?Submission_date . \n" },
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    private static final Map<String, String> predicateMap = Stream.of(new String[][] {
            { "Application_number", "?Application_number" },
            { "Applicant", "?Applicant" },
            { "Receipt_date", "?Receipt_date" },
            { "Serbian_patent_name", "?Serbian_patent_name" },
            { "English_patent_name", "?English_patent_name" },
            { "is_accepted", "?is_accepted = \"true\"" },
            { "Submission_date", "?Submission_date" },
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    /* Insert RDF data to an arbitrary named graph */
    private static final String UPDATE_TEMPLATE_NAMED_GRAPH = "INSERT DATA { GRAPH <%1$s> { %2$s } }";

    /* Simple SPARQL query on a named graph */
    private static final String SELECT_NAMED_GRAPH_TEMPLATE =
            "  SELECT ?Application_number \n" +
                    "  FROM <%1$s> WHERE { \n" +
                    "  %2$s " +
                    "  FILTER( %3$s )" +
                    "  }";

    private static final String SELECT_BY_APPLICATION_NUMBER =
            "  SELECT ?Application_number ?Applicant ?Receipt_date ?Serbian_patent_name ?English_patent_name \n" +
                    "  FROM <%1$s> WHERE { \n" +
                    "  ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionApplication_number> ?Application_number .\n" +
                    "  ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionApplicant> ?Applicant .\n" +
                    "  ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionReceipt_date> ?Receipt_date .\n" +
                    "  ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionSerbian_patent_name> ?Serbian_patent_name .\n" +
                    "  ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionEnglish_patent_name> ?English_patent_name .\n" +
                    "  FILTER( ?broj_prijave = \"%2$s\" )" +
                    "  }";

    private static final String SELECT_SUBMITTED =
            "  PREFIX p1: <http://www.XMLproject.ftn.uns.ac.rs.p1>" +
                    "  SELECT ?Application_number \n" +
                    "  FROM <%1$s> WHERE { \n" +
                    "  ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionApplication_number> ?Application_number .\n" +
                    "  ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionReceipt_date> ?date .\n" +
                    "  FILTER( \"%2$s\" < ?date && ?date< \"%3$s\" )\n" +
                    "  }";

    private static final String SELECT_SUBMITTED_RESPONDED =
            "  SELECT ?Application_number \n" +
                    "  FROM <%1$s> WHERE { \n" +
                    "  ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionApplication_number> ?Application_number .\n" +
                    "  ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionis_accepted> ?is_accepted . \n" +
                    "  ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionReceipt_date> ?date . \n" +
                    "  FILTER( ?is_accepted = \"%2$s\" && \"%3$s\" < ?date && ?date < \"%4$s\" )\n" +
                    "  }";

    /* An XML serialization format for RDF data */
    public static final String RDF_XML = "RDF/XML";

    public static String insertData(String graphURI, String ntriples) {
        return String.format(UPDATE_TEMPLATE_NAMED_GRAPH, graphURI, ntriples);
    }

    public static String selectDataByApplicationNumber(String graphURI, String brojPrijave) {
        return String.format(SELECT_BY_APPLICATION_NUMBER, graphURI, brojPrijave);
    }

    public static String selectSubmitted(String graphURI, String startDate, String endDate) {
        return String.format(SELECT_SUBMITTED, graphURI, startDate, endDate);
    }

    public static String selectSubmittedResponded(String graphURI, String startDate, String endDate, String condition) {
        return String.format(SELECT_SUBMITTED_RESPONDED, graphURI, startDate, endDate, condition);
    }

    public static String selectData(String graphURI, String condition) {
        String selector = selectorBuilder(condition);
        condition = conditionBuilder(condition);
        condition = operatorReplacement(condition);
        return String.format(SELECT_NAMED_GRAPH_TEMPLATE, graphURI, selector, condition);
    }

    private static String selectorBuilder(String condition) {
        String selector = " ?Request <http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognitionApplication_number> ?Application_number . \n";
        for (Map.Entry<String, String> set : predicateToSelector.entrySet()) {
            if (condition.contains(set.getKey())) {
                selector += set.getValue();
            }
        }
        return selector;
    }

    private static String conditionBuilder(String condition) {
        condition = condition.replace("is not accepted", "?is_accepted = \"false\"");
        for (Map.Entry<String, String> set : predicateMap.entrySet()) {
            condition = condition.replace(set.getKey(), set.getValue());
        }
        return condition;
    }

    private static String operatorReplacement(String condition) {
        condition = condition.replace(" and ", " && ");
        condition = condition.replace(" or ", " || ");
        condition = condition.replace(" is not ", " != ");
        condition = condition.replace(" is before ", " < ");
        condition = condition.replace(" is after ", " > ");
        condition = condition.replace(" is ", " = ");
        condition = condition.replace(" not ", " ! ");
        return condition;
    }
}
