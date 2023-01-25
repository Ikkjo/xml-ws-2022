package models.p.dto;

import javax.xml.bind.annotation.XmlElement;

public class TLegalEntityDTO extends TPersonDTO {

    @XmlElement(name = "Business_name")
    public String businessName;
}
