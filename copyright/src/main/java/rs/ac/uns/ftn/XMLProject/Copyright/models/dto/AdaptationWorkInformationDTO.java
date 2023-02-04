package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdaptationWorkInformationDTO {
    @XmlElement(name = "Original_work_title")
    public String originalWorkTitle;
    @XmlElement(name = "Original_work_author")
    public AuthorDTO originalWorkAuthor;

}
