package backend.patent.repository;

import backend.patent.model.solution.PatentSolution;

public class PatentSolutionRepository {
    PatentExistDBOperations exist = new PatentExistDBOperations();
    PatentFusekiOperations fuseki = new PatentFusekiOperations();

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
