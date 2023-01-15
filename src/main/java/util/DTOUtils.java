package util;

import models.a.CopyrightSubmissionRequest;
import models.a.CopyrightSubmissionRequest.WorkTitle;
import models.a.CopyrightSubmissionRequest.AdaptationWorkInformation;
import models.a.CopyrightSubmissionRequest.AuthorInformation;
import models.a.CopyrightSubmissionRequest.InformationForInstitution;
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

    public static CopyrightSubmissionRequest copyrightSubmissionRequestfromDTO(
            CopyrightSubmissionRequestDTO copyrightSubmissionRequestDTO) {
        CopyrightSubmissionRequest request = new CopyrightSubmissionRequest();

        request.setAuthorPseudonym(null); //copyrightSubmissionRequestDTO.getAuthorPseudonym());
        request.setFormOfRecordingWork(copyrightSubmissionRequestDTO.getFormOfRecordingWork());
        request.setWorkTitle(workTitleFromDTO(copyrightSubmissionRequestDTO.getWorkTitle()));
        request.setApplicant(TPersonFromDTO(copyrightSubmissionRequestDTO.getApplicant()));
        request.setAttorney(null); //TIndividualFromDTO(copyrightSubmissionRequestDTO.getAttorney()));
        request.setAdaptationWorkInformation(null);
        request.setWorkType(copyrightSubmissionRequestDTO.getWorkType());
        request.setAuthorInformation(null); //copyrightSubmissionRequestDTO.getAuthorInformation());
        request.setWorkMadeInBusinessRelationship(null); //copyrightSubmissionRequestDTO.workMadeInBusinessRelationship);
        request.setWayOfUsingWork(null); //copyrightSubmissionRequestDTO.getWayOfUsingWork());
        request.setInformationForInstitution(null); //copyrightSubmissionRequestDTO.getInformationForInstitution());
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

    public static CopyrightSubmissionRequestDTO copyrightSubmissionRequestToDTO(
            CopyrightSubmissionRequest copyrightSubmissionRequest) {
        return null;
    }

    public static WorkTitle workTitleFromDTO(WorkTitleDTO workTitleDTO) {
        WorkTitle workTitle = new WorkTitle();

        workTitle.setMainTitle(workTitleDTO.getMainTitle());
        workTitle.setAlternativeTitle(null); //workTitleDTO.getAlternativeTitle());

        return workTitle;
    }

    public static TIndividual TIndividualFromDTO(IndividualDTO individualDTO) {
        return null;
    }

    public static TPerson TPersonFromDTO(PersonDTO personDTO) {
        return null;
    }
}
