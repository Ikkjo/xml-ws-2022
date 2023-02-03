package rs.ac.uns.ftn.XMLProject.Copyright.repository;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.XMLProject.Copyright.models.solution.CopyrightRequestSolution;
import rs.ac.uns.ftn.XMLProject.Copyright.repository.util.CopyrightRequestSolutionExistDBOperations;
import rs.ac.uns.ftn.XMLProject.Copyright.repository.util.CopyrightRequestSolutionFusekiOperations;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Component
public class CopyrightRequestSolutionRepository {
    private final CopyrightRequestSolutionExistDBOperations existDB;
    private final CopyrightRequestSolutionFusekiOperations rdf;

    public CopyrightRequestSolutionRepository() {
        existDB = new CopyrightRequestSolutionExistDBOperations();
        rdf = new CopyrightRequestSolutionFusekiOperations();
    }

    public List<CopyrightRequestSolution> findAll() {
        try {
            return existDB.getAll();
        } catch (Exception e) {
            return null;
        }
    }

    public Optional<CopyrightRequestSolution> findById(String id) {
        try {
            return Optional.ofNullable(existDB.findById(id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean save(CopyrightRequestSolution copyrightRequestSolution) {
        boolean dbSuccess;
        boolean rdfSuccess;
        try {
            dbSuccess = existDB.save(copyrightRequestSolution);
            rdfSuccess = rdf.save(copyrightRequestSolution);
        } catch (Exception e) {
            return false;
        }

        return dbSuccess && rdfSuccess;
    }

    public BigInteger countAccepted(String startDate, String endDate) {
        return null;
    }

    public BigInteger countDeclined(String startDate, String endDate) {
        return null;
    }
}
