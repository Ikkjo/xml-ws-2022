package models.a.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    @XmlElement(name = "phoneNumber")
    protected String phoneNumber;
    @XmlElement(name = "email")
    protected String email;
    @XmlElement(name = "address")
    protected AddressDTO address;
}
