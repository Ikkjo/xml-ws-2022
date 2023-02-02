package backend.patent.model.p.dto;

import javax.xml.bind.annotation.XmlElement;

public class PriorityRightsRecognitionFromEarlierApplicationsDTO {

    @XmlElement(name = "Earlier_applications")
    public EarlierApplicationsDTO earlierApplications;
}
