package backend.patent.repository;

import backend.patent.model.solution.PatentSolution;
import org.springframework.stereotype.Repository;

@Repository
public class PatentSolutionRepository {
    private final PatentExistDBOperations exist;
    private final PatentFusekiOperations fuseki;

    public PatentSolutionRepository() {
        exist = new PatentExistDBOperations();
        fuseki = new PatentFusekiOperations();
    }

    public void save(PatentSolution solution) throws Exception {
        exist.save(solution, solution.getApplicationNumber(),"/db/xml/patent/solution", PatentSolution.class);
    }

    public PatentSolution findById(String id) throws Exception {
        return exist.findByIdSolution(id);
    }

    public long countSubmitted(String startDate, String endDate) throws Exception {
        return fuseki.countSubmitted(startDate, endDate);
    }

    public long countSubmittedResponded(String startDate, String endDate, String condition) throws Exception {
        return fuseki.countSubmittedResponded(startDate, endDate, condition);
    }
}
