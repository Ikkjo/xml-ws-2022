package dto;

import models.p.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Request_for_patent_recognition_DTO")
public class RequestForPatentRecognitionDTO {

    @XmlElement(name = "Information_for_institution")
    public InformationForInstitutionDTO informationForInstitutionDTO;
    @XmlElement(name = "Patent_information")
    public PatentInformationDTO patentInformationDTO;
    @XmlElement(name = "Proxy")
    public models.p.RequestForPatentRecognition.Proxy proxy;
    @XmlElement(name = "Delivery_address")
    public models.p.RequestForPatentRecognition.DeliveryAddress deliveryAddress;
    @XmlElement(name = "Delivery_type")
    public DeliveryType deliveryType;
    @XmlElement(name = "Application_information")
    public ApplicationInformation applicationInformation;
    @XmlElement(name = "Priority_rights_recognition_from_earlier_applications")
    public PriorityRightsRecognitionFromEarlierApplicationsDTO priorityRightsRecognitionFromEarlierApplicationsDTO;
    @XmlAttribute(name = "address")
    public String address;
    @XmlAttribute(name = "institution")
    public String institution;
}
