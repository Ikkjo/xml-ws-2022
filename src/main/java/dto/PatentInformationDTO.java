package dto;

import models.p.PatentInformation;
import models.p.PatentName;

import javax.xml.bind.annotation.XmlElement;

public class PatentInformationDTO {
    @XmlElement(name = "Patent_name")
    protected PatentName patentName;
    @XmlElement(name = "Applicant")
    protected PatentInformation.Applicant applicant;
    @XmlElement(name = "Inventor")
    protected PatentInformation.Inventor inventor;
}
