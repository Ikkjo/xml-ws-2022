package rs.ac.uns.ftn.XMLProject.Copyright.repository.util;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import rs.ac.uns.ftn.XMLProject.Copyright.models.solution.CopyrightRequestSolution;
import rs.ac.uns.ftn.XMLProject.Copyright.util.ExistDBAuthUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class CopyrightRequestSolutionExistDBOperations {
    private static final String collectionId = "/db/xml/copyright/solutions";

    private ExistDBAuthUtils.ConnectionProperties conn;

    public CopyrightRequestSolutionExistDBOperations() {
        try {
            conn = ExistDBAuthUtils.loadProperties();

        } catch (IOException e) {
            System.out.println("\n\nCould not read ExistDB properties!\n\n");
            e.printStackTrace();
        }

    }

    public CopyrightRequestSolution findById(String id) throws Exception {

        if (!connectToDatabase()) {
            return null;
        }

        CopyrightRequestSolution solution = null;
        String documentId = id + "-solution.xml";

        Collection col = null;
        XMLResource res;
        try {
            // get the collection
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");

            res = (XMLResource)col.getResource(documentId);


            if(res != null) {

                JAXBContext context = JAXBContext.newInstance(CopyrightRequestSolution.class);

                Unmarshaller unmarshaller = context.createUnmarshaller();

                solution = (CopyrightRequestSolution) unmarshaller.unmarshal(res.getContentAsDOM());

            }
        } finally {
            cleanup(col);
        }
        return solution;
    }

    public boolean save(CopyrightRequestSolution solution) throws Exception {

        if (!connectToDatabase()) {
            return false;
        }

        String documentId =  solution.getRequestNumber() + "-solution.xml";

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
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);

            JAXBContext context = JAXBContext.newInstance(CopyrightRequestSolution.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // marshal the contents to an output stream
            marshaller.marshal(solution, os);

            // link the stream to the XML resource
            res.setContent(os);
            col.storeResource(res);

        } finally {
            cleanup(col);
        }
        return true;
    }

    public ArrayList<CopyrightRequestSolution> getAll() throws Exception {

        if (!connectToDatabase()) {
            return null;
        }

        Collection col = null;

        CopyrightRequestSolution copyrightSubmissionRequest;
        ArrayList<CopyrightRequestSolution> copyrightSubmissionRequestArrayList;
        try {
            // get the collection
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");

            XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xPathQueryService.setProperty("indent", "yes");

            copyrightSubmissionRequestArrayList = new ArrayList<>();
            String[] resources = col.listResources();
            XMLResource res;
            for (String resourceId : resources) {
                res = (XMLResource) col.getResource(resourceId);
                JAXBContext context = JAXBContext.newInstance(CopyrightRequestSolution.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                copyrightSubmissionRequest = (CopyrightRequestSolution) unmarshaller.unmarshal(res.getContentAsDOM());
                copyrightSubmissionRequestArrayList.add(copyrightSubmissionRequest);
            }
        } finally {
            if (col != null) {
                col.close();
            }
        }
        return copyrightSubmissionRequestArrayList;
    }

    private boolean connectToDatabase() {
        try{

            // initialize database driver
            Class<?> cl = Class.forName(conn.driver);

            Database database = (Database) cl.newInstance();
            database.setProperty("create-database", "true");

            DatabaseManager.registerDatabase(database);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void cleanup(Collection col) throws XMLDBException {
        if(col != null) {
            col.close();
        }
    }

    private Collection getOrCreateCollection(ExistDBAuthUtils.ConnectionProperties conn, String collectionUri) throws XMLDBException {
        return getOrCreateCollection(conn, collectionUri, 0);
    }

    private Collection getOrCreateCollection(ExistDBAuthUtils.ConnectionProperties conn, String collectionUri, int pathSegmentOffset) throws XMLDBException {

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
}
