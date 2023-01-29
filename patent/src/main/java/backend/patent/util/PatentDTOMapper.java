package backend.patent.util;

import backend.patent.model.p.*;
import backend.patent.model.p.dto.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class PatentDTOMapper {

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
        if (request.getApplicant() instanceof TIndividual) {
            TIndividualDTO applicantDTO = new TIndividualDTO();
            applicantDTO.firstName = ((TIndividual) request.getApplicant()).getFirstName();
            applicantDTO.lastName = ((TIndividual) request.getApplicant()).getLastName();
            applicantDTO.email = request.getApplicant().getEmail();
            applicantDTO.phoneNumber = request.getApplicant().getPhoneNumber();
            applicantDTO.faxNumber = request.getApplicant().getFaxNumber();
            applicantDTO.address = addressToDTO(request.getApplicant().getAddress());
            applicantDTO.citizenship = ((TIndividual) request.getApplicant()).getCitizenship();
            requestDTO.applicant = applicantDTO;
        } else if (request.getApplicant() instanceof TLegalEntity) {
            TLegalEntityDTO applicantDTO = new TLegalEntityDTO();
            applicantDTO.businessName = ((TLegalEntity) request.getApplicant()).getBusinessName();
            applicantDTO.email = request.getApplicant().getEmail();
            applicantDTO.phoneNumber = request.getApplicant().getPhoneNumber();
            applicantDTO.faxNumber = request.getApplicant().getFaxNumber();
            applicantDTO.address = addressToDTO(request.getApplicant().getAddress());
            requestDTO.applicant = applicantDTO;
        }
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

    public RequestForPatentRecognition PatentRecognitionRequestFromDTO(CreatePatentRecognitionRequestDTO requestDTO) throws DatatypeConfigurationException {
        RequestForPatentRecognition request = new RequestForPatentRecognition();

        request.getInformationForInstitution().setSubmissionDate(getCurrentDate());

        // Naziv pronalaska
        RequestForPatentRecognition.PatentName patentName = new RequestForPatentRecognition.PatentName();
        patentName.setSerbianPatentName(requestDTO.patentName.serbianPatentName);
        patentName.setEnglishPatentName(requestDTO.patentName.englishPatentName);
        request.setPatentName(patentName);

        // Podnosilac
        if (requestDTO.applicant instanceof TIndividualDTO) {
            TIndividual applicant = new TIndividual();
            applicant.setFirstName(((TIndividualDTO) requestDTO.applicant).firstName);
            applicant.setLastName(((TIndividualDTO) requestDTO.applicant).lastName);
            applicant.setAddress(addressFromDTO(requestDTO.applicant.address));
            applicant.setEmail(requestDTO.applicant.email);
            applicant.setPhoneNumber(requestDTO.applicant.phoneNumber);
            applicant.setFaxNumber(requestDTO.applicant.faxNumber);
            applicant.setCitizenship(((TIndividualDTO) requestDTO.applicant).citizenship);
            request.setApplicant(applicant);
        } else if (requestDTO.applicant instanceof TLegalEntityDTO) {
            TLegalEntity applicant = new TLegalEntity();
            applicant.setBusinessName(((TLegalEntityDTO) requestDTO.applicant).businessName);
            applicant.setAddress(addressFromDTO(requestDTO.applicant.address));
            applicant.setEmail(requestDTO.applicant.email);
            applicant.setPhoneNumber(requestDTO.applicant.phoneNumber);
            applicant.setFaxNumber(requestDTO.applicant.faxNumber);
            request.setApplicant(applicant);
        }

        // Pronalazac
        RequestForPatentRecognition.Inventor inventor = new RequestForPatentRecognition.Inventor();
        inventor.setDoesNotWantToBeListed(requestDTO.inventor.doesNotWantToBeListed);
        inventor.setFirstName(requestDTO.inventor.firstName);
        inventor.setLastName(requestDTO.inventor.lastName);
        inventor.setAddress(addressFromDTO(requestDTO.inventor.address));
        inventor.setEmail(requestDTO.inventor.email);
        inventor.setPhoneNumber(requestDTO.inventor.phoneNumber);
        inventor.setFaxNumber(requestDTO.inventor.faxNumber);
        request.setInventor(inventor);

        // Punomocnik
        RequestForPatentRecognition.Proxy proxy = new RequestForPatentRecognition.Proxy();
        proxy.setProxyForRepresentation(requestDTO.proxy.proxyForRepresentation);
        proxy.setAttorneyForReceivingLetters(requestDTO.proxy.attorneyForReceivingLetters);
        proxy.setFirstName(requestDTO.proxy.firstName);
        proxy.setLastName(requestDTO.proxy.lastName);
        proxy.setAddress(addressFromDTO(requestDTO.proxy.address));
        proxy.setEmail(requestDTO.proxy.email);
        proxy.setPhoneNumber(requestDTO.proxy.phoneNumber);
        request.setProxy(proxy);

        // Adresa za dostavljanje
        RequestForPatentRecognition.DeliveryAddress deliveryAddress = new RequestForPatentRecognition.DeliveryAddress();
        deliveryAddress.setAddress(addressFromDTO(requestDTO.deliveryAddress.address));
        request.setDeliveryAddress(deliveryAddress);

        // Nacin dostavljanja
        DeliveryType deliveryType = new DeliveryType();
        deliveryType.setDeliveryInPaperForm(requestDTO.deliveryType.deliveryInPaperForm);
        deliveryType.setElectronicDelivery(requestDTO.deliveryType.electronicDelivery);
        request.setDeliveryType(deliveryType);

        // Prijava
        ApplicationInformation applicationInformation = new ApplicationInformation();
        applicationInformation.setSeparateApplication(requestDTO.applicationInformation.separateApplication);
        applicationInformation.setSupplementaryApplication(requestDTO.applicationInformation.supplementaryApplication);
        applicationInformation.setOriginalApplicationNumber(requestDTO.applicationInformation.originalApplicationNumber);
        applicationInformation.setOriginalApplicationSubmissionDate(requestDTO.applicationInformation.originalApplicationSubmissionDate);
        request.setApplicationInformation(applicationInformation);

        // Zahtev za priznanje prava prvenstva iz ranijih prijava
        EarlierApplications earlierApplications = new EarlierApplications();
        //earlierApplications.getEarlierApplication();
        for (EarlierApplicationDTO earlierApplicationDTO : requestDTO.priorityRightsRecognitionFromEarlierApplications.earlierApplications.earlierApplication) {
            EarlierApplications.EarlierApplication earlierApplication = new EarlierApplications.EarlierApplication();
            earlierApplication.setEarlierApplicationNumber(earlierApplicationDTO.earlierApplicationNumber);
            earlierApplication.setEarlierApplicationSubmissionDate(earlierApplicationDTO.earlierApplicationSubmissionDate);
            earlierApplication.setCountryOrOrganizationDesignation(earlierApplicationDTO.countryOrOrganizationDesignation);
            earlierApplications.getEarlierApplication().add(earlierApplication);
        }
        PriorityRightsRecognitionFromEarlierApplications priorityRightsRecognitionFromEarlierApplications = new PriorityRightsRecognitionFromEarlierApplications();
        priorityRightsRecognitionFromEarlierApplications.setEarlierApplications(earlierApplications);
        request.setPriorityRightsRecognitionFromEarlierApplications(priorityRightsRecognitionFromEarlierApplications);

        return request;
    }

    private static Address addressFromDTO(AddressDTO addressDTO){
        Address address = new Address();
        address.setStreet(addressDTO.street);
        address.setStreetNumber(addressDTO.streetNumber);
        address.setCity(addressDTO.city);
        address.setZipCode(addressDTO.zipCode);
        address.setDrzava(addressDTO.drzava);
        return address;
    }

    private XMLGregorianCalendar getCurrentDate() throws DatatypeConfigurationException {

        Date now = new Date();
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(now);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

    }
}
