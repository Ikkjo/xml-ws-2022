package backend.patent.model.p.dto;

import backend.patent.model.p.EarlierApplications;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;

@Data
@NoArgsConstructor
public class EarlierApplicationDTO {

    @XmlElement(name = "Earlier_application_submission_date")
    public XMLGregorianCalendar earlierApplicationSubmissionDate;
    @XmlElement(name = "Earlier_application_number")
    public String earlierApplicationNumber;
    @XmlElement(name = "Country_or_organization_designation")
    public String countryOrOrganizationDesignation;

    public EarlierApplicationDTO(EarlierApplications.EarlierApplication earlierApplication) {
        this.earlierApplicationNumber = earlierApplication.getEarlierApplicationNumber();
        this.earlierApplicationSubmissionDate = earlierApplication.getEarlierApplicationSubmissionDate();
        this.countryOrOrganizationDesignation = earlierApplication.getCountryOrOrganizationDesignation();
    }
}
