package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

@AllArgsConstructor
@Data
public class CopyrightRequestSolutionDTO {
    @XmlElement(name = "request_number", required = true)
    protected String requestNumber;
    protected boolean accepted;
    @XmlElement(required = true)
    protected OfficialDTO official;
    @XmlElement(name = "solution_date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar solutionDate;
    protected String motivation;
}
