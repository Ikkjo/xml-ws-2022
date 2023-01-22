package util;

import models.p.Address;
import models.p.EarlierApplications;
import models.p.RequestForPatentRecognition;
import models.p.dto.*;

import java.util.ArrayList;

public class PatentDTOUtils {

    public ArrayList<RequestForPatentRecognitionDTO> patentRecognitionRequestsToDTOList(
            ArrayList<RequestForPatentRecognition> requests) {
        ArrayList<RequestForPatentRecognitionDTO> requestsDTO =  new ArrayList<>();
        for (RequestForPatentRecognition req: requests) {
            requestsDTO.add(patentRecognitionRequestToDTO(req));
        }
        return requestsDTO;
    }

    public static RequestForPatentRecognitionDTO patentRecognitionRequestToDTO(RequestForPatentRecognition request){
        RequestForPatentRecognitionDTO requestDTO = new RequestForPatentRecognitionDTO();

        requestDTO.address = request.getAddress();
        requestDTO.institution = request.getInstitution();

        // Informacije za instituciju
        InformationForInstitutionDTO informationForInstitutionDTO = new InformationForInstitutionDTO();
        informationForInstitutionDTO.applicationNumber = request.getInformationForInstitution().getApplicationNumber();
        informationForInstitutionDTO.receiptDate = request.getInformationForInstitution().getReceiptDate();
        informationForInstitutionDTO.submissionDate = request.getInformationForInstitution().getSubmissionDate();
        informationForInstitutionDTO.stampAndSignature = request.getInformationForInstitution().getStampAndSignature();
        requestDTO.informationForInstitution = informationForInstitutionDTO;

        // Naziv pronalaska
        PatentNameDTO patentNameDTO = new PatentNameDTO();
        patentNameDTO.serbianPatentName = request.getPatentName().getSerbianPatentName();
        patentNameDTO.englishPatentName = request.getPatentName().getEnglishPatentName();
        requestDTO.patentName = patentNameDTO;

        // Podnosilac
        // NOTE: Srediti ime, prezime, poslovno ime, drzavljanstvo
        TPersonDTO applicantDTO = new TPersonDTO();
        applicantDTO.email = request.getApplicant().getEmail();
        applicantDTO.phoneNumber = request.getApplicant().getPhoneNumber();
        applicantDTO.faxNumber = request.getApplicant().getFaxNumber();
        applicantDTO.address = addressToDTO(request.getApplicant().getAddress());
        requestDTO.applicant = applicantDTO;

        // Pronalazac
        InventorDTO inventorDTO = new InventorDTO();
        inventorDTO.firstName = request.getInventor().getFirstName();
        inventorDTO.lastName = request.getInventor().getLastName();
        inventorDTO.email = request.getInventor().getEmail();
        inventorDTO.phoneNumber = request.getInventor().getPhoneNumber();
        inventorDTO.faxNumber = request.getInventor().getFaxNumber();
        inventorDTO.address = addressToDTO(request.getInventor().getAddress());
        inventorDTO.doesNotWantToBeListed = request.getInventor().getDoesNotWantToBeListed();
        requestDTO.inventor = inventorDTO;

        // Punomocnik
        ProxyDTO proxyDTO = new ProxyDTO();
        proxyDTO.firstName = request.getProxy().getFirstName();
        proxyDTO.lastName = request.getProxy().getLastName();
        proxyDTO.email = request.getProxy().getEmail();
        proxyDTO.phoneNumber = request.getProxy().getPhoneNumber();
        proxyDTO.address = addressToDTO(request.getProxy().getAddress());
        proxyDTO.attorneyForReceivingLetters = request.getProxy().getAttorneyForReceivingLetters();
        proxyDTO.proxyForRepresentation = request.getProxy().getProxyForRepresentation();
        requestDTO.proxy = proxyDTO;

        // Adresa za dostavljanje
        DeliveryAddressDTO deliveryAddressDTO = new DeliveryAddressDTO();
        deliveryAddressDTO.address = addressToDTO(request.getDeliveryAddress().getAddress());
        requestDTO.deliveryAddress = deliveryAddressDTO;

        // Nacin dostavljanja
        DeliveryTypeDTO deliveryTypeDTO = new DeliveryTypeDTO();
        deliveryTypeDTO.deliveryInPaperForm = request.getDeliveryType().getDeliveryInPaperForm();
        deliveryTypeDTO.electronicDelivery = request.getDeliveryType().getElectronicDelivery();
        requestDTO.deliveryType = deliveryTypeDTO;

        // Prijava
        ApplicationInformationDTO applicationInformationDTO = new ApplicationInformationDTO();
        applicationInformationDTO.originalApplicationNumber = request.getApplicationInformation().getOriginalApplicationNumber();
        applicationInformationDTO.originalApplicationSubmissionDate = request.getApplicationInformation().getOriginalApplicationSubmissionDate();
        applicationInformationDTO.separateApplication = request.getApplicationInformation().getSeparateApplication();
        applicationInformationDTO.supplementaryApplication = request.getApplicationInformation().getSupplementaryApplication();
        requestDTO.applicationInformation = applicationInformationDTO;

        // Zahtev za priznanje prava prvenstva iz ranijih prijava
        EarlierApplicationsDTO earlierApplicationsDTO = new EarlierApplicationsDTO();
        earlierApplicationsDTO.earlierApplication = new ArrayList<>();
        for(EarlierApplications.EarlierApplication earlierApplication : request.getPriorityRightsRecognitionFromEarlierApplications().getEarlierApplications().getEarlierApplication()){
            EarlierApplicationDTO earlierApplicationDTO = new EarlierApplicationDTO();
            earlierApplicationDTO.earlierApplicationNumber = earlierApplication.getEarlierApplicationNumber();
            earlierApplicationDTO.earlierApplicationSubmissionDate = earlierApplication.getEarlierApplicationSubmissionDate();
            earlierApplicationDTO.countryOrOrganizationDesignation = earlierApplication.getCountryOrOrganizationDesignation();
            earlierApplicationsDTO.earlierApplication.add(earlierApplicationDTO);
        }
        PriorityRightsRecognitionFromEarlierApplicationsDTO priorityRightsRecognitionFromEarlierApplicationsDTO = new PriorityRightsRecognitionFromEarlierApplicationsDTO();
        priorityRightsRecognitionFromEarlierApplicationsDTO.earlierApplications = earlierApplicationsDTO;
        requestDTO.priorityRightsRecognitionFromEarlierApplications = priorityRightsRecognitionFromEarlierApplicationsDTO;

        return requestDTO;
    }

    private static AddressDTO addressToDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.street = address.getStreet();
        addressDTO.streetNumber = address.getStreetNumber();
        addressDTO.city = address.getCity();
        addressDTO.zipCode = address.getZipCode();
        addressDTO.drzava = address.getDrzava();
        return addressDTO;
    }
}
