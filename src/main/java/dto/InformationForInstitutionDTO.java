package dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

public class InformationForInstitutionDTO {
    @XmlElement(name = "Application_number")
    public String applicationNumber;
    @XmlElement(name = "Receipt_date")
    @XmlSchemaType(name = "date")
    public XMLGregorianCalendar receiptDate;
    @XmlElement(name = "Submission_date")
    @XmlSchemaType(name = "date")
    public XMLGregorianCalendar submissionDate;
    @XmlElement(name = "Stamp_and_signature")
    public Object stampAndSignature;
}
