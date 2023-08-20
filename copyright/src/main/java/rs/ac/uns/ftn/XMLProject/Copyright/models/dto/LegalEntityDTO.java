package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "legalEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class LegalEntityDTO {

    @XmlElement(name = "phoneNumber")
    public String phoneNumber;
    @XmlElement(name = "faxNumber")
    public String faxNumber;
    @XmlElement(name = "email")
    public String email;
    @XmlElement(name = "address")
    public AddressDTO address;
    @XmlElement(name = "businessName")
    protected String businessName;
}
