package backend.patent.model.p.dto;

import javax.xml.bind.annotation.XmlElement;

public class TIndividualDTO extends TPersonDTO {

    @XmlElement(name = "Last_name")
    public String lastName;
    @XmlElement(name = "Citizenship")
    public String citizenship;
    @XmlElement(name = "First_name")
    public String firstName;
}
