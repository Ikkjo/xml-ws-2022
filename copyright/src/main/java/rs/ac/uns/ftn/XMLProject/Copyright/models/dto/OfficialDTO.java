package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlRootElement(name = "official")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfficialDTO {
    @XmlElement(name = "firstName")
    public String firstName;
    @XmlElement(name = "lastName")
    public String lastName;
}
