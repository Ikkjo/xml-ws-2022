package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@AllArgsConstructor
@Data
public class OfficialDTO {
    @XmlElement()
    public String firstName;
    @XmlElement()
    public String lastName;
}
