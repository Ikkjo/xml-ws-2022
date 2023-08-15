package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualDTO {

    @XmlElement(name = "Phone_number")
    public String phoneNumber;
    @XmlElement(name = "Fax_number")
    public String faxNumber;
    @XmlElement(name = "Email")
    public String email;
    @XmlElement(name = "Address")
    public AddressDTO address;
    @XmlElement(name = "lastName")
    protected String lastName;
    @XmlElement(name = "citizenship")
    protected String citizenship;
    @XmlElement(name = "firstName")
    protected String firstName;
}
