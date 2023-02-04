package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;

@AllArgsConstructor
@Data
public class OfficialDTO {
    @XmlElement(name = "first_name", required = true)
    public String firstName;
    @XmlElement(name = "last_name", required = true)
    public String lastName;
}
