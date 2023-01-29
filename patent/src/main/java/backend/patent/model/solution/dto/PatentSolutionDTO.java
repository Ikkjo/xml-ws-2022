package backend.patent.model.solution.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlRootElement(name = "PatentSolutionDTO")
public class PatentSolutionDTO {

    @XmlElement(name = "Is_accepted")
    public boolean isAccepted;
    @XmlElement(name = "Application_number")
    public String applicationNumber;
    @XmlElement(name = "Official")
    public String official;
    @XmlElement(name = "Accepted_or_rejected_date")
    public XMLGregorianCalendar acceptedOrRejectedDate;
    @XmlElement(name = "Explanation")
    public String explanation;
}
