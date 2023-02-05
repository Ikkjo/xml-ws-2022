package backend.patent.model.p.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;

public class InformationForInstitutionDTO {

    @XmlElement(name = "Application_number")
    public String applicationNumber;
    @XmlElement(name = "Receipt_date")
    public XMLGregorianCalendar receiptDate;
    @XmlElement(name = "Submission_date")
    public XMLGregorianCalendar submissionDate;
}
