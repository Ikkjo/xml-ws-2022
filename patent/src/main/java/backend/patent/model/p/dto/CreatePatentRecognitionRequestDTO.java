package backend.patent.model.p.dto;

import javax.xml.bind.annotation.XmlElement;

public class CreatePatentRecognitionRequestDTO {

    @XmlElement(name = "Patent_name")
    public PatentNameDTO patentName;
    @XmlElement
    public TLegalEntityDTO applicantLegalEntity;
    @XmlElement
    public TIndividualDTO applicantIndividual;
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
}
