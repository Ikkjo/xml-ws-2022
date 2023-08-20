package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "address")
public class AddressDTO {

    @XmlElement(name = "street")
    public String street;
    @XmlElement(name = "streetNumber")
    public BigInteger streetNumber;
    @XmlElement(name = "city")
    public String city;
    @XmlElement(name = "zipCode")
    public int zipCode;
    @XmlElement(name = "country", nillable = true)
    public String country;
}
