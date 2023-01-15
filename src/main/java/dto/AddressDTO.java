package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

public class AddressDTO {
    @XmlElement(name = "Street")
    public String street;
    @XmlElement(name = "Street_number")
    public BigInteger streetNumber;
    @XmlElement(name = "City")
    public String city;
    @XmlElement(name = "Zip_code")
    public int zipCode;
    @XmlElement(name = "Drzava")
    public String drzava;
}
