package backend.patent.model.p.dto;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class EarlierApplicationsDTO {

    @XmlElement(name = "Earlier_application")
    public List<EarlierApplicationDTO> earlierApplication;
}
