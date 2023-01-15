package repository;

import models.a.CopyrightSubmissionRequest;

import java.util.Collection;
import java.util.List;
public class CopyrightSubmissionRequestRepository {

    private final CopyrightSubmissionRequestExistDBOperations existDB;
    private final CopyrightSubmissionRequestFusekiOperations rdf;

    public CopyrightSubmissionRequestRepository() {
        existDB = new CopyrightSubmissionRequestExistDBOperations();
        rdf = new CopyrightSubmissionRequestFusekiOperations();
    }

    public List<CopyrightSubmissionRequest> findAll() {
        try {
            return existDB.getAll();
        } catch (Exception e) {
            return null;
        }
    }

    public CopyrightSubmissionRequest findById(String id) {
        try {
            return existDB.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean save(CopyrightSubmissionRequest copyrightSubmissionRequest) {
        boolean dbSuccess = false;
        boolean rdfSuccess = false;
        try {
            dbSuccess = existDB.save(copyrightSubmissionRequest);
            rdfSuccess = rdf.save(copyrightSubmissionRequest);
        } catch (Exception e) {
            return false;
        }

        return dbSuccess && rdfSuccess;
    }
}
