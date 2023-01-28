package models.p.dto;

import javax.xml.bind.annotation.XmlElement;

public class DeliveryAddressDTO {

    @XmlElement(name = "Address")
    public AddressDTO address;
}
