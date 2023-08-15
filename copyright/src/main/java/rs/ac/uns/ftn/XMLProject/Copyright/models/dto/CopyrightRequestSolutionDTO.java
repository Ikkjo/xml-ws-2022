package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.*;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlRootElement(name = "copyrightRequestSolution")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CopyrightRequestSolutionDTO {
    @XmlElement(name = "request_number", required = true)
    public String requestNumber;
    public boolean accepted;
    public OfficialDTO official;
    @XmlSchemaType(name = "date")
    public XMLGregorianCalendar solutionDate;
    public String motivation;
}
