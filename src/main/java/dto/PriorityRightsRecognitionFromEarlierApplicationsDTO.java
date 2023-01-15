package dto;

import models.p.EarlierApplications;

import javax.xml.bind.annotation.XmlElement;

public class PriorityRightsRecognitionFromEarlierApplicationsDTO {

    @XmlElement(name = "Earlier_applications")
    public EarlierApplications earlierApplications;
}
