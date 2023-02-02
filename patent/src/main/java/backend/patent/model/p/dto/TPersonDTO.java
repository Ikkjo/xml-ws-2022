package backend.patent.model.p.dto;

import javax.xml.bind.annotation.XmlElement;

public class TPersonDTO {

    @XmlElement(name = "Phone_number")
    public String phoneNumber;
    @XmlElement(name = "Email")
    public String email;
    @XmlElement(name = "Address")
    public AddressDTO address;
    @XmlElement(name = "Fax_number")
    public String faxNumber;
}
