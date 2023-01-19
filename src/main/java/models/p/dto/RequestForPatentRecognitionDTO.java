package models.p.dto;

import models.p.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class RequestForPatentRecognitionDTO {

    @XmlElement(name = "Information_for_institution")
    public InformationForInstitutionDTO informationForInstitution;
    @XmlElement(name = "Patent_name")
    public PatentNameDTO patentName;
    @XmlElement(name = "Applicant")
    public TPersonDTO applicant;
    @XmlElement(name = "Inventor")
    public InventorDTO inventor;
    @XmlElement(name = "Proxy")
    public ProxyDTO proxy;
    @XmlElement(name = "Delivery_address")
    public DeliveryAddressDTO deliveryAddress;
    @XmlElement(name = "Delivery_type")
    public DeliveryTypeDTO deliveryType;
    @XmlElement(name = "Application_information")
    public ApplicationInformationDTO applicationInformation;
    @XmlElement(name = "Priority_rights_recognition_from_earlier_applications")
    public PriorityRightsRecognitionFromEarlierApplicationsDTO priorityRightsRecognitionFromEarlierApplications;
    @XmlAttribute(name = "address")
    public String address;
    @XmlAttribute(name = "institution")
    public String institution;
}
