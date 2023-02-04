package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TPerson")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersonDTO {
    @XmlElement(name = "Phone_number")
    public String phoneNumber;
    @XmlElement(name = "Fax_umber")
    public String faxNumber;
    @XmlElement(name = "Email")
    public String email;
    @XmlElement(name = "Address")
    public AddressDTO address;
}
