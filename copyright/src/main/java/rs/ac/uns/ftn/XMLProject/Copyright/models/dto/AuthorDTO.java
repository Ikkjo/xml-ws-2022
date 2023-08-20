package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "author")
public class AuthorDTO {

    @XmlElement(name = "authorSurname")
    public String authorSurname;
    @XmlElement(name = "authorName")
    public String authorName;
    @XmlElement(name = "citizenship")
    public String citizenship;
    @XmlElement(name = "dateOfDeath")
    public XMLGregorianCalendar dateOfDeath;
    @XmlAttribute(name = "anonymous")
    public Boolean anonymous;
    @XmlAttribute(name = "isAlive")
    public Boolean isAlive;
}
