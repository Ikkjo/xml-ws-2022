package backend.patent.model.p.dto;

import javax.xml.bind.annotation.XmlElement;

public class PatentNameDTO {

    @XmlElement(name = "Serbian_patent_name")
    public String serbianPatentName;
    @XmlElement(name = "English_patent_name")
    public String englishPatentName;
}
