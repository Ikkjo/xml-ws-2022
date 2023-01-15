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
public class AdaptationWorkInformationDTO {
    @XmlElement(name = "originalWorkTitle")
    protected String originalWorkTitle;
    @XmlElement(name = "originalWorkAuthor")
    protected AuthorDTO originalWorkAuthor;

}
