package backend.patent.repository;

import backend.patent.model.p.RequestForPatentRecognition;
import backend.patent.model.solution.PatentSolution;
import backend.patent.util.AuthenticationUtilities;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class PatentExistDBOperations {

    public RequestForPatentRecognition findById(String id) throws Exception {
        RequestForPatentRecognition request = null;

        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        // initialize collection and document identifiers
        String collectionId = "/db/xml/patent/";
        String documentId = id + ".xml";

        // initialize database driver
        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;
        XMLResource res = null;

        try {
            // get the collection
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");

            res = (XMLResource)col.getResource(documentId);

            if(res == null) {
                System.out.println("[WARNING] Document '" + documentId + "' can not be found!");
            } else {

                JAXBContext context = JAXBContext.newInstance("backend.patent.model.p");

                Unmarshaller unmarshaller = context.createUnmarshaller();

                request = (RequestForPatentRecognition) unmarshaller.unmarshal(res.getContentAsDOM());


            }
        } finally {
            cleanup(col);
        }
        return request;
    }

    public void save(Object object, String applicationNumber, String collectionId, String contextPath) throws Exception {

        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        String documentId = applicationNumber + ".xml";

        // initialize database driver
        Class<?> cl = Class.forName(conn.driver);

        // encapsulation of the database driver functionality
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);

        // a collection of Resources stored within an XML database
        Collection col = null;
        XMLResource res;
        OutputStream os = new ByteArrayOutputStream();

        try {
            col = getOrCreateCollection(conn, collectionId);

            /*
             *  create new XMLResource with a given id
             *  an id is assigned to the new resource if left empty (null)
             */
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);

            JAXBContext context = JAXBContext.newInstance(contextPath);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // marshal the contents to an output stream
            marshaller.marshal(object, os);

            // link the stream to the XML resource
            res.setContent(os);

            col.storeResource(res);

        } finally {
            cleanup(col);
        }
    }

    private void cleanup(Collection col) {
        if(col != null) {
            try {
                col.close();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }

    private static Collection getOrCreateCollection(AuthenticationUtilities.ConnectionProperties conn, String collectionUri) throws XMLDBException {
        return getOrCreateCollection(conn, collectionUri, 0);
    }

    private static Collection getOrCreateCollection(AuthenticationUtilities.ConnectionProperties conn, String collectionUri, int pathSegmentOffset) throws XMLDBException {

        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);

        // create the collection if it does not exist
        if(col == null) {

            if(collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String[] pathSegments = collectionUri.split("/");

            if(pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for(int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/").append(pathSegments[i]);
                }

                Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);

                if (startCol == null) {

                    // child collection does not exist

                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(conn, collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }

    public ArrayList<RequestForPatentRecognition> getAll() throws Exception {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        // initialize collection and document identifiers
        String collectionId = "/db/xml/patent/";

        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;

        RequestForPatentRecognition request;
        ArrayList<RequestForPatentRecognition> requests;
        try {
            // get the collection
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");

            XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xPathQueryService.setProperty("indent", "yes");

            requests = new ArrayList<>();
            String[] resources = col.listResources();
            XMLResource res;
            for (String resourceId : resources) {
                res = (XMLResource) col.getResource(resourceId);
                JAXBContext context = JAXBContext.newInstance(RequestForPatentRecognition.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                request = (RequestForPatentRecognition) unmarshaller.unmarshal(res.getContentAsDOM());
                requests.add(request);
            }
        } finally {
            if (col != null) {
                col.close();
            }
        }
        return requests;
    }

    public PatentSolution findByIdSolution(String id) throws Exception {

        PatentSolution solution = null;

        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        // initialize collection and document identifiers
        String collectionId = "/db/xml-project/patent/solution";
        String documentId = id + ".xml";

        // initialize database driver
        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;
        XMLResource res;
        try {
            // get the collection
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");

            res = (XMLResource)col.getResource(documentId);


            if(res != null) {

                JAXBContext context = JAXBContext.newInstance("backend.patent.model.solution");

                Unmarshaller unmarshaller = context.createUnmarshaller();

                solution = (PatentSolution) unmarshaller.unmarshal(res.getContentAsDOM());

            }
        } finally {
            cleanup(col);
        }
        return solution;
    }

    public ArrayList<RequestForPatentRecognition> search(String content) throws Exception {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
        String filter = "/*[contains(., '" + content + "')] | " +
                "/*[contains(@Application_number, '" + content + "')] | " +
                "/*[contains(@Receipt_date, '" + content + "')] | " +
                "/*[contains(@Submission_date, '" + content + "')] | " +
                "/*[contains(@Citizenship, '" + content + "')]";

        // initialize collection and document identifiers
        String collectionId = "/db/xml-project/patent";

        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;

        RequestForPatentRecognition requestForPatentRecognition;
        ArrayList<RequestForPatentRecognition> requestsForPatentRecognition;
        try {
            // get the collection
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");

            XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xPathQueryService.setProperty("indent", "yes");
            ResourceSet result = xPathQueryService.query(filter);
            ResourceIterator iterator = result.getIterator();

            requestsForPatentRecognition = new ArrayList<>();
            XMLResource res;
            while (iterator.hasMoreResources()) {
                res = (XMLResource) iterator.nextResource();
                JAXBContext context = JAXBContext.newInstance(RequestForPatentRecognition.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                requestForPatentRecognition = (RequestForPatentRecognition) unmarshaller.unmarshal(res.getContentAsDOM());
                requestsForPatentRecognition.add(requestForPatentRecognition);
            }
        } finally {
            if (col != null) {
                col.close();
            }
        }
        return requestsForPatentRecognition;
    }
}
