package util;

import models.a.*;
import models.a.CopyrightSubmissionRequest.WorkTitle;
import models.a.dto.*;

import java.util.List;
import java.util.stream.Collectors;

public class CopyrightDTOMapper {

    private static final ObjectFactory factory = new ObjectFactory();

    public static CopyrightSubmissionRequest copyrightSubmissionRequestFromDTO(
            CopyrightSubmissionRequestDTO copyrightSubmissionRequestDTO) {
        CopyrightSubmissionRequest request = factory.createCopyrightSubmissionRequest();

        request.setAuthorPseudonym(copyrightSubmissionRequestDTO.getAuthorPseudonym());
        request.setFormOfRecordingWork(copyrightSubmissionRequestDTO.getFormOfRecordingWork());
        request.setWorkTitle(workTitleFromDTO(copyrightSubmissionRequestDTO.getWorkTitle()));
        request.setApplicant(TPersonFromDTO(copyrightSubmissionRequestDTO.getApplicant()));
        request.setAttorney(TIndividualFromDTO(copyrightSubmissionRequestDTO.getAttorney()));
        request.setAdaptationWorkInformation(
                AdaptationWorkInformationFromDTO(copyrightSubmissionRequestDTO.getAdaptationWorkInformation()));
        request.setWorkType(copyrightSubmissionRequestDTO.getWorkType());
        request.setAuthor(TAuthorFromDTO(copyrightSubmissionRequestDTO.getAuthor()));
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
        return copyrightSubmissionRequests.stream().map(
                        CopyrightDTOMapper::copyrightSubmissionRequestToDTO)
                .collect(Collectors.toList());
    }

    public static CopyrightSubmissionRequestDTO copyrightSubmissionRequestToDTO(
            CopyrightSubmissionRequest copyrightSubmissionRequest) {
        CopyrightSubmissionRequestDTO dto = new CopyrightSubmissionRequestDTO();

        dto.setAuthorPseudonym(copyrightSubmissionRequest.getAuthorPseudonym());
        dto.setFormOfRecordingWork(copyrightSubmissionRequest.getFormOfRecordingWork());
        dto.setWorkTitle(workTitleToDTO(copyrightSubmissionRequest.getWorkTitle()));
        dto.setApplicant(TPersonToDTO(copyrightSubmissionRequest.getApplicant()));
        dto.setAttorney(TIndividualToDTO(copyrightSubmissionRequest.getAttorney()));
        dto.setAdaptationWorkInformation(
                AdaptationWorkInformationToDTO(copyrightSubmissionRequest.getAdaptationWorkInformation()));
        dto.setWorkType(copyrightSubmissionRequest.getWorkType());
        dto.setAuthor(TAuthorToDTO(copyrightSubmissionRequest.getAuthor()));
        dto.setWorkMadeInBusinessRelationship(copyrightSubmissionRequest.isWorkMadeInBusinessRelationship());
        dto.setWayOfUsingWork(copyrightSubmissionRequest.getWayOfUsingWork());
        dto.setRequestNumber(copyrightSubmissionRequest.getRequestNumber());
        dto.setRequestSubmissionDate(copyrightSubmissionRequest.getRequestSubmissionDate());
        dto.setInstitution(copyrightSubmissionRequest.getInstitution());
        dto.setAddress(copyrightSubmissionRequest.getAddress());

        return dto;
    }

    private static AdaptationWorkInformationDTO AdaptationWorkInformationToDTO(
            CopyrightSubmissionRequest.AdaptationWorkInformation adaptationWorkInformation) {
        AdaptationWorkInformationDTO dto = new AdaptationWorkInformationDTO();

        dto.setOriginalWorkAuthor(TAuthorToDTO(adaptationWorkInformation.getOriginalWorkAuthor().getValue()));
        dto.setOriginalWorkTitle(adaptationWorkInformation.getOriginalWorkTitle());

        return dto;
    }

    private static AuthorDTO TAuthorToDTO(TAuthor author) {
        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setAnonymous(author.isAnonymous());
        authorDTO.setAuthorName(author.getAuthorName());
        authorDTO.setAuthorSurname(author.getAuthorSurname());
        authorDTO.setCitizenship(author.getCitizenship());
        authorDTO.setIsAlive(author.isAlive());
        authorDTO.setDateOfDeath(author.getDateOfDeath().getValue());

        return authorDTO;
    }

    private static IndividualDTO TIndividualToDTO(TIndividual individual) {
        IndividualDTO dto = new IndividualDTO();

        dto.setEmail(individual.getEmail());
        dto.setCitizenship(individual.getCitizenship());
        dto.setAddress(AddressToDTO(individual.getAddress()));
        dto.setPhoneNumber(individual.getPhoneNumber());
        dto.setFaxNumber(individual.getFaxNumber());
        dto.setFirstName(individual.getFirstName());
        dto.setLastName(individual.getLastName());

        return dto;
    }

    private static AddressDTO AddressToDTO(Address address) {
        AddressDTO dto = new AddressDTO();

        dto.setCity(address.getCity());
        dto.setDrzava(address.getDrzava());
        dto.setStreet(address.getStreet());
        dto.setZipCode(address.getZipCode());
        dto.setStreetNumber(address.getStreetNumber());

        return null;
    }

    private static PersonDTO TPersonToDTO(TPerson person) {

        if (person instanceof TIndividual) {
            return TIndividualToDTO((TIndividual) person);
        } else {
            LegalEntityDTO dto = new LegalEntityDTO();

            dto.setAddress(AddressToDTO(person.getAddress()));
            dto.setEmail(person.getEmail());
            dto.setFaxNumber(person.getFaxNumber());
            dto.setPhoneNumber(person.getPhoneNumber());
            dto.setBusinessName(((TLegalEntity) person).getBusinessName());

        }
        return null;
    }

    private static WorkTitleDTO workTitleToDTO(WorkTitle workTitle) {
        WorkTitleDTO dto = new WorkTitleDTO();

        dto.setMainTitle(workTitle.getMainTitle());
        dto.setAlternativeTitle(workTitle.getAlternativeTitle());

        return dto;
    }

    public static WorkTitle workTitleFromDTO(WorkTitleDTO workTitleDTO) {
        WorkTitle workTitle = factory.createCopyrightSubmissionRequestWorkTitle();

        workTitle.setMainTitle(workTitleDTO.getMainTitle());
        workTitle.setAlternativeTitle(workTitleDTO.getAlternativeTitle());

        return workTitle;
    }

    public static TIndividual TIndividualFromDTO(IndividualDTO individualDTO) {
        TIndividual individual = factory.createTIndividual();

        individual.setCitizenship(individualDTO.getCitizenship());
        individual.setFirstName(individualDTO.getFirstName());
        individual.setLastName(individualDTO.getLastName());
        individual.setEmail(individualDTO.getEmail());
        individual.setPhoneNumber(individualDTO.getPhoneNumber());
        individual.setFaxNumber(individualDTO.getFaxNumber());

        return individual;
    }
    
    public static TPerson TPersonFromDTO(PersonDTO personDTO) {

        if (personDTO instanceof IndividualDTO) {
            return TIndividualFromDTO((IndividualDTO) personDTO);
        } else {
            LegalEntityDTO legalEntityDTO = (LegalEntityDTO) personDTO;

            TLegalEntity legalEntity = factory.createTLegalEntity();

            legalEntity.setEmail(legalEntityDTO.getEmail());
            legalEntity.setAddress(AddressFromDTO(legalEntityDTO.getAddress()));
            legalEntity.setFaxNumber(legalEntityDTO.getFaxNumber());
            legalEntity.setPhoneNumber(legalEntityDTO.getPhoneNumber());
            legalEntity.setBusinessName(legalEntityDTO.getBusinessName());

            return legalEntity;
        }
    }

    public static Address AddressFromDTO(AddressDTO addressDTO) {
        Address address = factory.createAddress();

        address.setCity(addressDTO.getCity());
        address.setDrzava(addressDTO.getDrzava());
        address.setStreetNumber(addressDTO.getStreetNumber());
        address.setStreet(addressDTO.getStreet());
        address.setZipCode(addressDTO.getZipCode());

        return address;
    }

    public static CopyrightSubmissionRequest.AdaptationWorkInformation AdaptationWorkInformationFromDTO(
            AdaptationWorkInformationDTO adaptationWorkInformationDTO) {
        CopyrightSubmissionRequest.AdaptationWorkInformation adaptationWorkInformation =
                factory.createCopyrightSubmissionRequestAdaptationWorkInformation();

        adaptationWorkInformation.setOriginalWorkAuthor(
                factory.createCopyrightSubmissionRequestAdaptationWorkInformationOriginalWorkAuthor
                        (TAuthorFromDTO(adaptationWorkInformationDTO.getOriginalWorkAuthor())));
        adaptationWorkInformation.setOriginalWorkTitle(adaptationWorkInformationDTO.getOriginalWorkTitle());

        return adaptationWorkInformation;
    }

    public static TAuthor TAuthorFromDTO(AuthorDTO authorDTO) {
        TAuthor author = factory.createTAuthor();

        author.setAuthorName(authorDTO.getAuthorName());
        author.setAuthorSurname(authorDTO.getAuthorSurname());
        author.setAnonymous(authorDTO.getAnonymous());
        author.setCitizenship(authorDTO.getCitizenship());
        author.setIsAlive(authorDTO.getIsAlive());
        author.setDateOfDeath(factory.createTAuthorDateOfDeath(authorDTO.getDateOfDeath()));

        return author;
    }
}
