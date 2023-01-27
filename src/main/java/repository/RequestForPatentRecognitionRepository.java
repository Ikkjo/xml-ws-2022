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
        fuseki.save(request, "metadata.xsl");
    }

    public ArrayList<RequestForPatentRecognition> getAll() throws Exception {
        return exist.getAll();
    }

    public String createRdfString(String id) {
        try {
            RequestForPatentRecognition request = exist.findById(id);
            if (request.getIsAccepted() == null)
                fuseki.generateRdf(request, "metadata.xsl");
            else
                fuseki.generateRdf(request, "update_metadata.xsl");
            return fuseki.getRdfString(request.getInformationForInstitution().getApplicationNumber());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
