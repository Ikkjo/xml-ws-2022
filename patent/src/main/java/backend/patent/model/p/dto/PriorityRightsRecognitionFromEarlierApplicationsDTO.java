package backend.patent.model.p.dto;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class PriorityRightsRecognitionFromEarlierApplicationsDTO {

    @XmlElement(name = "earlierApplications")
    public List<EarlierApplicationDTO> earlierApplications;
}
