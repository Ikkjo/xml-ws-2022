package models.a.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @XmlElement(name = "street")
    protected String street;
    @XmlElement(name = "streetNumber")
    protected int streetNumber;
    @XmlElement(name = "city")
    protected String city;
    @XmlElement(name = "zipCode")
    protected int zipCode;
    @XmlElement(name = "drzava")
    protected String drzava;
}
