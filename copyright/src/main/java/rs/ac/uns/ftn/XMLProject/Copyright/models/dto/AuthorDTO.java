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

    @XmlElement(name = "Author_surname")
    public String authorSurname;
    @XmlElement(name = "Author_name")
    public String authorName;
    @XmlElement(name = "Citizenship", required = true)
    public String citizenship;
    @XmlElement(name = "Date_of_death")
    public XMLGregorianCalendar dateOfDeath;
    @XmlAttribute(name = "anonymous")
    public Boolean anonymous;
    @XmlAttribute(name = "is_alive")
    public Boolean isAlive;
}
