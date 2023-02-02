package backend.patent.repository;

import backend.patent.model.solution.PatentSolution;
import org.springframework.stereotype.Repository;

@Repository
public class PatentSolutionRepository {
    private final PatentExistDBOperations exist;
    private final PatentFusekiOperations fuseki;

    public PatentSolutionRepository(PatentExistDBOperations exist, PatentFusekiOperations fuseki) {
        this.exist = exist;
        this.fuseki = fuseki;
    }

    public void save(PatentSolution solution) throws Exception {
        exist.save(solution, solution.getApplicationNumber(),"/db/xml-project/patent/solution", "backend.patent.model.solution");
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
