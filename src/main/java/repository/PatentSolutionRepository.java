package repository;

import models.solution.PatentSolution;

public class PatentSolutionRepository {
    PatentExistDBOperations exist = new PatentExistDBOperations();
    PatentFusekiOperations fuseki = new PatentFusekiOperations();

    public void save(PatentSolution solution) throws Exception {
        exist.save(solution, solution.getApplicationNumber(),"/db/xml-project/patent/solution", "models.solution");
    }

    public PatentSolution findById(String id) throws Exception {
        return exist.findByIdSolution(id);
    }
}
