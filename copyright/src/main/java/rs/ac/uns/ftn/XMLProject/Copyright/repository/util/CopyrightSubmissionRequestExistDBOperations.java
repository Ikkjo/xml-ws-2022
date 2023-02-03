package rs.ac.uns.ftn.XMLProject.Copyright.repository.util;

import org.xmldb.api.base.*;
import rs.ac.uns.ftn.XMLProject.Copyright.models.a.CopyrightSubmissionRequest;
import rs.ac.uns.ftn.XMLProject.Copyright.util.ExistDBAuthUtils;
import rs.ac.uns.ftn.XMLProject.Copyright.util.ExistDBAuthUtils.ConnectionProperties;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class CopyrightSubmissionRequestExistDBOperations {

    private static final String collectionId = "/db/xml/copyright/";

    private ConnectionProperties conn;

    public CopyrightSubmissionRequestExistDBOperations() {
        try {
            conn = ExistDBAuthUtils.loadProperties();

        } catch (IOException e) {
            System.out.println("\n\nCould not read ExistDB properties!\n\n");
            e.printStackTrace();
        }

    }

    public CopyrightSubmissionRequest findById(String id) throws Exception {

        if (!connectToDatabase()) {
            return null;
        }

        CopyrightSubmissionRequest request = null;
        String documentId = id + ".xml";

        Collection col = null;
        XMLResource res;
        try {
            // get the collection
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");

            res = (XMLResource)col.getResource(documentId);


            if(res != null) {

                JAXBContext context = JAXBContext.newInstance("models.a");

                Unmarshaller unmarshaller = context.createUnmarshaller();

                request = (CopyrightSubmissionRequest) unmarshaller.unmarshal(res.getContentAsDOM());

            }
        } finally {
            cleanup(col);
        }
        return request;
    }

    public boolean save(CopyrightSubmissionRequest request) throws Exception {

        if (!connectToDatabase()) {
             return false;
        }

        String documentId =  request.getRequestNumber() + ".xml";

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

            JAXBContext context = JAXBContext.newInstance("models.a");

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // marshal the contents to an output stream
            marshaller.marshal(request, os);

            // link the stream to the XML resource
            res.setContent(os);
            col.storeResource(res);

        } finally {
            cleanup(col);
        }
        return true;
    }

    public ArrayList<CopyrightSubmissionRequest> getAll() throws Exception {

        if (!connectToDatabase()) {
            return null;
        }

        Collection col = null;

        CopyrightSubmissionRequest copyrightSubmissionRequest;
        ArrayList<CopyrightSubmissionRequest> copyrightSubmissionRequestArrayList;
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
                JAXBContext context = JAXBContext.newInstance(CopyrightSubmissionRequest.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                copyrightSubmissionRequest = (CopyrightSubmissionRequest) unmarshaller.unmarshal(res.getContentAsDOM());
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

    public List<CopyrightSubmissionRequest> search(String content) throws Exception {
        ExistDBAuthUtils.ConnectionProperties conn = ExistDBAuthUtils.loadProperties();

        String query = "/*[contains(., '" + content + "')] | " +
                "/*[contains(@request_number, '" + content + "')] | " +
                "/*[contains(@submission_date, '" + content + "')] | "+
                "/*[contains(/Work_title/Main_title, '" + content + "')]";

        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;

        CopyrightSubmissionRequest request;
        List<CopyrightSubmissionRequest> requests;
        try {
            // get the collection
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");

            XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xPathQueryService.setProperty("indent", "yes");
            ResourceSet result = xPathQueryService.query(query);
            ResourceIterator iterator = result.getIterator();

            requests = new ArrayList<>();
            XMLResource res;
            while (iterator.hasMoreResources()) {
                res = (XMLResource) iterator.nextResource();
                JAXBContext context = JAXBContext.newInstance(CopyrightSubmissionRequest.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                request = (CopyrightSubmissionRequest) unmarshaller.unmarshal(res.getContentAsDOM());
                requests.add(request);
            }
        } finally {
            if (col != null) {
                col.close();
            }
        }
        return requests;
    }

    public List<CopyrightSubmissionRequest> search(List<String> rdfQuery) throws Exception{
        ExistDBAuthUtils.ConnectionProperties conn = ExistDBAuthUtils.loadProperties();

        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;

        CopyrightSubmissionRequest request;
        List<CopyrightSubmissionRequest> requests;
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
                if (rdfQuery.contains(resourceId)) {
                    res = (XMLResource) col.getResource(resourceId);
                    JAXBContext context =
                            JAXBContext.newInstance(CopyrightSubmissionRequest.class);
                    Unmarshaller unmarshaller = context.createUnmarshaller();
                    request = (CopyrightSubmissionRequest) unmarshaller.unmarshal(res.getContentAsDOM());
                    requests.add(request);
                }
            }
        } finally {
            if (col != null) {
                col.close();
            }
        }
        return requests;
    }
}
