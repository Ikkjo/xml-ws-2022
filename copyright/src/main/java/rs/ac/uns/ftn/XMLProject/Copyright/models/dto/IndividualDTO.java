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
@XmlRootElement(name = "individual")
@XmlAccessorType(XmlAccessType.FIELD)
public class IndividualDTO {

    @XmlElement(name = "phoneNumber")
    public String phoneNumber;
    @XmlElement(name = "faxNumber")
    public String faxNumber;
    @XmlElement(name = "email")
    public String email;
    @XmlElement(name = "address")
    public AddressDTO address;
    @XmlElement(name = "lastName")
    protected String lastName;
    @XmlElement(name = "citizenship")
    protected String citizenship;
    @XmlElement(name = "firstName")
    protected String firstName;
}
