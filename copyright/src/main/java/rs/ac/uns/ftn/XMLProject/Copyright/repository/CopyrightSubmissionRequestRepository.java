package rs.ac.uns.ftn.XMLProject.Copyright.repository;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.XMLProject.Copyright.exception.ResourceNotFoundException;
import rs.ac.uns.ftn.XMLProject.Copyright.models.a.CopyrightSubmissionRequest;
import rs.ac.uns.ftn.XMLProject.Copyright.repository.util.CopyrightSubmissionRequestExistDBOperations;
import rs.ac.uns.ftn.XMLProject.Copyright.repository.util.CopyrightSubmissionRequestFusekiOperations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
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

    public Optional<CopyrightSubmissionRequest> findById(String id) {
        try {
            return Optional.ofNullable(existDB.findById(id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean save(CopyrightSubmissionRequest copyrightSubmissionRequest) {
        boolean dbSuccess;
        boolean rdfSuccess;
        try {
            dbSuccess = existDB.save(copyrightSubmissionRequest);
            rdfSuccess = rdf.save(copyrightSubmissionRequest);
        } catch (Exception e) {
            return false;
        }

        return dbSuccess && rdfSuccess;
    }

    public BigInteger countSubmitted(String startDate, String endDate) throws Exception {
        return rdf.countSubmitted(startDate, endDate);
    }

    public boolean update(CopyrightSubmissionRequest request) {
        boolean dbSuccess;
        boolean rdfSuccess;
        try {
            dbSuccess = existDB.save(request);
            rdfSuccess = rdf.update(request);
        } catch (Exception e) {
            return false;
        }

        return dbSuccess && rdfSuccess;
    }

    public String getRdfMetadata(String requestNumber) {
        try {
            CopyrightSubmissionRequest request = existDB.findById(requestNumber);
            // implement check for if solution exists ???
            return rdf.getRdfString(requestNumber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getJsonMetadata(String requestNumber) {
        try {
            return rdf.getJsonString(requestNumber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<CopyrightSubmissionRequest> search(String content) {
        try {
            return existDB.search(content);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<CopyrightSubmissionRequest> searchMetadata(String query) {
        try {
            return existDB.search(rdf.rdfQuery(query));
        } catch (Exception e){
            return new ArrayList<>();
        }
    }
}
