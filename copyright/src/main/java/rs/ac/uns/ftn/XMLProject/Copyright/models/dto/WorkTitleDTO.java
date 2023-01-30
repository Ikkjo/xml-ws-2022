package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkTitleDTO {
    @XmlElement(name = "mainTitle")
    protected String mainTitle;
    @XmlElement(name = "alternativeTitle")
    protected String alternativeTitle;
}
