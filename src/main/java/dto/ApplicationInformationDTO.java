package dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

public class ApplicationInformationDTO {

    @XmlElement(name = "Original_application_number")
    public String originalApplicationNumber;
    @XmlElement(name = "Original_application_submission_date")
    @XmlSchemaType(name = "date")
    public XMLGregorianCalendar originalApplicationSubmissionDate;
    @XmlAttribute(name = "supplementary_application")
    @XmlSchemaType(name = "anySimpleType")
    public String supplementaryApplication;
    @XmlAttribute(name = "separate_application")
    @XmlSchemaType(name = "anySimpleType")
    public String separateApplication;
}
