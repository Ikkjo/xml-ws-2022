package repository;

import models.p.RequestForPatentRecognition;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class RequestForPatentRecognitionRepository {

    PatentExistDBOperations exist = new PatentExistDBOperations();
    PatentFusekiOperations fuseki = new PatentFusekiOperations();

    public RequestForPatentRecognition findById(String id) throws Exception {
        return exist.findById(id);
    }

    public void save(RequestForPatentRecognition request) throws Exception {
        exist.save(request);
        fuseki.save(request);
    }

    public ArrayList<RequestForPatentRecognition> getAll() throws Exception {
        return exist.getAll();
    }
}
