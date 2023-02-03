package rs.ac.uns.ftn.XMLProject.Copyright.models.dto;

import lombok.*;
import rs.ac.uns.ftn.XMLProject.Copyright.models.a.CopyrightSubmissionRequest;
import rs.ac.uns.ftn.XMLProject.Copyright.models.a.TAuthor;
import rs.ac.uns.ftn.XMLProject.Copyright.models.a.TIndividual;
import rs.ac.uns.ftn.XMLProject.Copyright.models.a.TPerson;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;



@XmlRootElement(name = "Copyright_submission_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopyrightSubmissionRequestDTO {
    @XmlElement(name = "Applicant")
    public PersonDTO applicant;
    @XmlElement(name = "Attorney")
    public IndividualDTO attorney;
    @XmlElement(name = "Author_pseudonym")
    public String authorPseudonym;
    @XmlElement(name = "Work_title")
    public WorkTitleDTO workTitle;
    @XmlElement(name = "Adaptation_work_information")
    public AdaptationWorkInformationDTO adaptationWorkInformation;
    @XmlElement(name = "Work_type")
    public String workType;
    @XmlElement(name = "Form_of_recording_work")
    public String formOfRecordingWork;
    @XmlElement(name = "Author")
    public AuthorDTO author;
    @XmlElement(name = "Work_made_in_business_relationship")
    public boolean workMadeInBusinessRelationship;
    @XmlElement(name = "Way_of_using_work")
    public String wayOfUsingWork;
    @XmlAttribute(name = "institution")
    public String institution;
    @XmlAttribute(name = "address")
    public String address;
    @XmlAttribute(name = "request_number")
    public String requestNumber;
    @XmlAttribute(name = "request_submission_date")
    @XmlSchemaType(name = "date")
    public XMLGregorianCalendar requestSubmissionDate;
}
