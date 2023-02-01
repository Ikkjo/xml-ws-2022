package backend.authorization.repository;

import backend.authorization.exceptions.UserNotFoundException;
import backend.authorization.model.SystemUser;
import backend.authorization.util.AuthenticationUtilities;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
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

public class AuthorizationExistDBOperations {

    private final String collectionId = "/db/xml-project/users";

    public SystemUser findUserByEmail(String email) throws Exception {
        SystemUser user = null;

        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        String username = email.split("@")[0] + ".xml";

        // initialize database driver
        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;
        try {
            // get the collection
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");

            XMLResource res = (XMLResource) col.getResource(username);


            if (res != null) {

                JAXBContext context = JAXBContext.newInstance("backend.authorization.model");

                Unmarshaller unmarshaller = context.createUnmarshaller();

                user = (SystemUser) unmarshaller.unmarshal(res.getContentAsDOM());

            }
        } finally {
            cleanup(col);
        }
        if (user == null)
            throw new UserNotFoundException();
        return user;
    }

    private void cleanup(Collection col) throws XMLDBException {
        if (col != null) {
            col.close();
        }
    }

    public void save(SystemUser user) throws Exception {

        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        String username = user.getEmail().split("@")[0] + ".xml";

        Class<?> cl = Class.forName(conn.driver);

        // encapsulation of the database driver functionality
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        // entry point for the API which enables you to get the Collection reference
        DatabaseManager.registerDatabase(database);

        // a collection of Resources stored within an XML database
        Collection col = null;
        OutputStream os = new ByteArrayOutputStream();

        try {
            col = getOrCreateCollection(conn, collectionId);
            XMLResource res = (XMLResource) col.createResource(username, XMLResource.RESOURCE_TYPE);

            JAXBContext context = JAXBContext.newInstance("backend.authorization.model");

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // marshal the contents to an output stream
            marshaller.marshal(user, os);

            // link the stream to the XML resource
            res.setContent(os);
            col.storeResource(res);

        } finally {
            cleanup(col);
        }
    }

    public ArrayList<SystemUser> getAll() throws Exception {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;

        ArrayList<SystemUser> users;
        try {
            // get the collection
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            col.setProperty(OutputKeys.INDENT, "yes");

            XPathQueryService xPathQueryService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xPathQueryService.setProperty("indent", "yes");

            users = new ArrayList<>();
            String[] resources = col.listResources();

            for (String resourceId : resources) {
                XMLResource res = (XMLResource) col.getResource(resourceId);
                JAXBContext context = JAXBContext.newInstance(SystemUser.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                SystemUser user = (SystemUser) unmarshaller.unmarshal(res.getContentAsDOM());
                users.add(user);
            }
        } finally {
            if (col != null) {
                col.close();
            }
        }
        return users;
    }

    private Collection getOrCreateCollection(AuthenticationUtilities.ConnectionProperties conn, String collectionUri) throws XMLDBException {
        return getOrCreateCollection(conn, collectionUri, 0);
    }

    private Collection getOrCreateCollection(AuthenticationUtilities.ConnectionProperties conn, String collectionUri, int pathSegmentOffset) throws XMLDBException {

        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);

        // create the collection if it does not exist
        if (col == null) {
            if (collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String[] pathSegments = collectionUri.split("/");

            if (pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for (int i = 0; i <= pathSegmentOffset; i++) {
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
