package models.a.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualDTO extends PersonDTO {
    @XmlElement(name = "lastName")
    protected String lastName;
    @XmlElement(name = "citizenship")
    protected String citizenship;
    @XmlElement(name = "firstName")
    protected String firstName;
}
