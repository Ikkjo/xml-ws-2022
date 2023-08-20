package backend.patent.repository;

import backend.patent.model.p.RequestForPatentRecognition;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@SuppressWarnings("deprecation")
public class RequestForPatentRecognitionRepository {

    private final PatentExistDBOperations exist;
    private final PatentFusekiOperations fuseki;

    public RequestForPatentRecognitionRepository(PatentExistDBOperations exist, PatentFusekiOperations fuseki) {
        this.exist = exist;
        this.fuseki = fuseki;
    }

    public RequestForPatentRecognition findById(String id) throws Exception {
        return exist.findById(id);
    }

    public void save(RequestForPatentRecognition request) throws Exception {
        exist.save(request,
                   request.getInformationForInstitution().getApplicationNumber(),
                   "/db/xml/patent/request",
                   RequestForPatentRecognition.class);
        fuseki.save(request, "MetaData.xsl");
    }

    public ArrayList<RequestForPatentRecognition> getAll() throws Exception {
        return exist.getAll();
    }

    public String createRdfString(String id) {
        try {
            RequestForPatentRecognition request = exist.findById(id);
            if (request.getIsAccepted() == null)
                fuseki.generateRdf(request, "MetaData.xsl");
            else
                fuseki.generateRdf(request, "UpdateMetaData.xsl");
            return fuseki.getRdfString(request.getInformationForInstitution().getApplicationNumber());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(RequestForPatentRecognition request) throws Exception {
        exist.save(request, request.getInformationForInstitution().getApplicationNumber(), "/db/xml/patent/request", RequestForPatentRecognition.class);
        fuseki.save(request, "UpdateMetaData.xsl");
    }

    public String getJsonString(String id) {
        try {
            return fuseki.getJsonString(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<RequestForPatentRecognition> searchByContent(String content) {
        try {
            return exist.search(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<RequestForPatentRecognition> search(String condition) {
        try {
            return exist.search(fuseki.executeRdfQuery(condition));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
