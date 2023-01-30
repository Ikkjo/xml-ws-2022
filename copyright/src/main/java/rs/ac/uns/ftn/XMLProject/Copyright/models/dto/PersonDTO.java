package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersonDTO {
    @XmlElement(name = "phoneNumber")
    protected String phoneNumber;
    @XmlElement(name = "faxNumber")
    protected String faxNumber;
    @XmlElement(name = "email")
    protected String email;
    @XmlElement(name = "address")
    protected AddressDTO address;
}