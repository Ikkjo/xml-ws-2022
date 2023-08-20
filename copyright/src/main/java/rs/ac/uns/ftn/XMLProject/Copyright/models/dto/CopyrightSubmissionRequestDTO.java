package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.*;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;



@XmlRootElement(name = "copyrightSubmissionRequest")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopyrightSubmissionRequestDTO {
    @XmlElement
    public LegalEntityDTO applicantLegalEntity;
    @XmlElement
    public IndividualDTO applicantIndividual;
    @XmlElement
    public IndividualDTO attorney;
    @XmlElement
    public String authorPseudonym;
    @XmlElement
    public WorkTitleDTO workTitle;
    @XmlElement
    public AdaptationWorkInformationDTO adaptationWorkInformation;
    @XmlElement
    public String workType;
    @XmlElement
    public String formOfRecordingWork;
    @XmlElement
    public AuthorDTO author;
    @XmlElement
    public boolean workMadeInBusinessRelationship;
    @XmlElement
    public String wayOfUsingWork;
    @XmlElement
    public String institution;
    @XmlElement
    public String address;
    @XmlElement
    public String requestNumber;
    @XmlElement
    public Boolean accepted;
    @XmlElement
    @XmlSchemaType(name = "date")
    public XMLGregorianCalendar requestSubmissionDate;
}
