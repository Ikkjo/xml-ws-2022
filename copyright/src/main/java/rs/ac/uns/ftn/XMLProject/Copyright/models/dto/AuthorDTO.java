package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    @XmlElement(name = "authorSurname")
    protected String authorSurname;
    @XmlElement(name = "authorName")
    protected String authorName;
    @XmlElement(name = "Citizenship", required = true)
    protected String citizenship;
    @XmlElement(name = "dateOfDeath")
    protected XMLGregorianCalendar dateOfDeath;
    @XmlAttribute(name = "anonymous")
    protected Boolean anonymous;
    @XmlAttribute(name = "isAlive")
    protected Boolean isAlive;
}
