package models.p.dto;

import javax.xml.bind.annotation.XmlElement;

public class AddressDTO {

    @XmlElement(name = "Street")
    public String street;
    @XmlElement(name = "Street_number")
    public int streetNumber;
    @XmlElement(name = "City")
    public String city;
    @XmlElement(name = "Zip_code")
    public int zipCode;
    @XmlElement(name = "Drzava")
    public String drzava;

}
