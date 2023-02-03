package rs.ac.uns.ftn.XMLProject.Copyright.repository;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.XMLProject.Copyright.models.a.CopyrightSubmissionRequest;
import rs.ac.uns.ftn.XMLProject.Copyright.repository.util.CopyrightSubmissionRequestExistDBOperations;
import rs.ac.uns.ftn.XMLProject.Copyright.repository.util.CopyrightSubmissionRequestFusekiOperations;

import java.math.BigInteger;
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

    public BigInteger countSubmitted(String startDate, String endDate) {
        return null;
    }
}
