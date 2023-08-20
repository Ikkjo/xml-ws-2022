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
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "adaptationWorkInformation")
@XmlAccessorType(XmlAccessType.FIELD)
public class AdaptationWorkInformationDTO {
    @XmlElement(name = "originalWorkTitle")
    public String originalWorkTitle;
    @XmlElement(name = "originalWorkAuthor")
    public AuthorDTO originalWorkAuthor;

}
