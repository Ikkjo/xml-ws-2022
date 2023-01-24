package models.a.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CopyrightSubmissionRequestDTO {
    @XmlElement(name = "applicant")
    public PersonDTO applicant;
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
    @XmlElement(name = "authorInformation")
    public AuthorDTO authorInformation;
    @XmlElement(name = "workMadeInBusinessRelationship")
    public Boolean workMadeInBusinessRelationship;
    @XmlElement(name = "wayOfUsingWork")
    public String wayOfUsingWork;
    @XmlAttribute(name = "request_number")
    protected String requestNumber;
    @XmlAttribute(name = "request_submission_date")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar requestSubmissionDate;
    @XmlAttribute(name = "institution")
    protected String institution;
    @XmlAttribute(name = "address")
    protected String address;
}
