package util;

import models.a.CopyrightSubmissionRequest;
import models.a.CopyrightSubmissionRequest.WorkTitle;
import models.a.CopyrightSubmissionRequest.AdaptationWorkInformation;
import models.a.CopyrightSubmissionRequest.AuthorInformation;
import models.a.ObjectFactory;
import models.a.TIndividual;
import models.a.TPerson;
import models.a.dto.CopyrightSubmissionRequestDTO;
import models.a.dto.IndividualDTO;
import models.a.dto.PersonDTO;
import models.a.dto.WorkTitleDTO;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

public class DTOUtils {

    private static final ObjectFactory factory = new ObjectFactory();

    public static CopyrightSubmissionRequest copyrightSubmissionRequestfromDTO(
            CopyrightSubmissionRequestDTO copyrightSubmissionRequestDTO) {
        CopyrightSubmissionRequest request = factory.createCopyrightSubmissionRequest();

        request.setAuthorPseudonym(copyrightSubmissionRequestDTO.getAuthorPseudonym());
        request.setFormOfRecordingWork(copyrightSubmissionRequestDTO.getFormOfRecordingWork());
        request.setWorkTitle(workTitleFromDTO(copyrightSubmissionRequestDTO.getWorkTitle()));
        request.setApplicant(TPersonFromDTO(copyrightSubmissionRequestDTO.getApplicant()));
        request.setAttorney(TIndividualFromDTO(copyrightSubmissionRequestDTO.getAttorney()));
        request.setAdaptationWorkInformation(factory.createCopyrightSubmissionRequestAdaptationWorkInformation());
        request.setWorkType(copyrightSubmissionRequestDTO.getWorkType());
        request.setAuthorInformation(null); //copyrightSubmissionRequestDTO.getAuthorInformation());
        request.setWorkMadeInBusinessRelationship(copyrightSubmissionRequestDTO.getWorkMadeInBusinessRelationship());
        request.setWayOfUsingWork(copyrightSubmissionRequestDTO.getWayOfUsingWork());
        request.setRequestNumber(copyrightSubmissionRequestDTO.getRequestNumber());
        request.setRequestSubmissionDate(copyrightSubmissionRequestDTO.getRequestSubmissionDate());
        request.setInstitution(copyrightSubmissionRequestDTO.getInstitution());
        request.setAddress(copyrightSubmissionRequestDTO.getAddress());

        return request;
    }

    public static List<CopyrightSubmissionRequestDTO> copyrightSubmissionRequestToDTOList(
            List<CopyrightSubmissionRequest> copyrightSubmissionRequests) {
        List<CopyrightSubmissionRequestDTO> retVal = new ArrayList<>();
        for (CopyrightSubmissionRequest req : copyrightSubmissionRequests) {
            retVal.add(copyrightSubmissionRequestToDTO(req));
        }
        return retVal;
    }

    // TODO: Implement
    public static CopyrightSubmissionRequestDTO copyrightSubmissionRequestToDTO(
            CopyrightSubmissionRequest copyrightSubmissionRequest) {
        return null;
    }

    public static WorkTitle workTitleFromDTO(WorkTitleDTO workTitleDTO) {
        WorkTitle workTitle = factory.createCopyrightSubmissionRequestWorkTitle();

        workTitle.setMainTitle(workTitleDTO.getMainTitle());
        workTitle.setAlternativeTitle(workTitleDTO.getAlternativeTitle());

        return workTitle;
    }


    // TODO: Implement
    public static TIndividual TIndividualFromDTO(IndividualDTO individualDTO) {
        return null;
    }


    // TODO: Implement
    public static TPerson TPersonFromDTO(PersonDTO personDTO) {
        return null;
    }
}
