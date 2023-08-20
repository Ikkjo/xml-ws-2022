package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.*;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlRootElement(name = "copyrightRequestSolution")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CopyrightRequestSolutionDTO {
    @XmlElement(name = "requestNumber", required = true)
    public String requestNumber;
    @XmlElement(name = "accepted")
    public boolean accepted;
    @XmlElement(name = "official")
    public OfficialDTO official;
    @XmlSchemaType(name = "date")
    public XMLGregorianCalendar solutionDate;
    @XmlElement(name = "motivation", nillable = true)
    public String motivation;
}
