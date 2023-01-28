package models.p.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;

public class ApplicationInformationDTO {

    @XmlElement(name = "Original_application_number")
    public String originalApplicationNumber;
    @XmlElement(name = "Original_application_submission_date")
    public XMLGregorianCalendar originalApplicationSubmissionDate;
    @XmlAttribute(name = "supplementary_application")
    public Boolean supplementaryApplication;
    @XmlAttribute(name = "separate_application")
    public Boolean separateApplication;
}
