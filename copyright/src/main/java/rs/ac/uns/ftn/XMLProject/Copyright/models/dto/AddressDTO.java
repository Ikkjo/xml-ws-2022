package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

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
