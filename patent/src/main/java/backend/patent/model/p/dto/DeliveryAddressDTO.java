package backend.patent.model.p.dto;

import javax.xml.bind.annotation.XmlElement;

public class DeliveryAddressDTO {

    @XmlElement(name = "Address")
    public AddressDTO address;
}
