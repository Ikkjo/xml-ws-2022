package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

@AllArgsConstructor
@Data
@XmlRootElement(name = "Copyright_request_solution")
public class CopyrightRequestSolutionDTO {
    @XmlElement(name = "request_number", required = true)
    public String requestNumber;
    public boolean accepted;
    @XmlElement(required = true)
    public OfficialDTO official;
    @XmlElement(name = "solution_date", required = true)
    @XmlSchemaType(name = "date")
    public XMLGregorianCalendar solutionDate;
    public String motivation;
}
