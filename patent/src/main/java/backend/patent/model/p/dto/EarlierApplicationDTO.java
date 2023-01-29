package backend.patent.model.p.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;

public class EarlierApplicationDTO {

    @XmlElement(name = "Earlier_application_submission_date")
    public XMLGregorianCalendar earlierApplicationSubmissionDate;
    @XmlElement(name = "Earlier_application_number")
    public String earlierApplicationNumber;
    @XmlElement(name = "Country_or_organization_designation")
    public String countryOrOrganizationDesignation;
}
