package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.*;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;



@XmlRootElement(name = "copyrightSubmissionRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopyrightSubmissionRequestDTO {
    @XmlElement(name = "applicantLegalEntity", nillable = true)
    public LegalEntityDTO applicantLegalEntity;
    @XmlElement(name = "applicantIndividual", nillable = true)
    public IndividualDTO applicantIndividual;
    @XmlElement(name = "attorney")
    public IndividualDTO attorney;
    @XmlElement(name = "authorPseudonym")
    public String authorPseudonym;
    @XmlElement(name = "workTitle")
    public WorkTitleDTO workTitle;
    @XmlElement(name = "adaptationWorkInformation")
    public AdaptationWorkInformationDTO adaptationWorkInformation;
    @XmlElement(name = "workType")
    public String workType;
    @XmlElement(name = "formOfRecordingWork")
    public String formOfRecordingWork;
    @XmlElement(name = "author")
    public AuthorDTO author;
    @XmlElement(name = "workMadeInBusinessRelationship")
    public boolean workMadeInBusinessRelationship;
    @XmlElement(name = "wayOfUsingWork")
    public String wayOfUsingWork;
    @XmlAttribute(name = "institution", required = true)
    public String institution;
    @XmlAttribute(name = "address", required = true)
    public String address;
    @XmlAttribute(name = "requestNumber", required = true)
    public String requestNumber;
    @XmlAttribute(name = "accepted", required = true)
    public Boolean accepted;
    @XmlElement
    @XmlSchemaType(name = "date")
    public XMLGregorianCalendar requestSubmissionDate;
}
