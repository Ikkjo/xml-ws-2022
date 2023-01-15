package models.a.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformationForInstitutionDTO {

    @XmlElement(name = "requestNumber")
    protected String requestNumber;
    @XmlElement(name = "requestSubmissionDate")
    protected XMLGregorianCalendar requestSubmissionDate;

}
