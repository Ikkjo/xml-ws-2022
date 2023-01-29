//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.27 at 03:30:28 PM CET 
//


package backend.patent.model.report;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Start_date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="End_date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Submitted_requests_number" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="Accepted_requests_number" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *         &lt;element name="Rejected_requests_number" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "startDate",
    "endDate",
    "submittedRequestsNumber",
    "acceptedRequestsNumber",
    "rejectedRequestsNumber"
})
@XmlRootElement(name = "Report")
public class Report {

    @XmlElement(name = "Start_date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "End_date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "Submitted_requests_number")
    @XmlSchemaType(name = "unsignedInt")
    protected long submittedRequestsNumber;
    @XmlElement(name = "Accepted_requests_number")
    @XmlSchemaType(name = "unsignedInt")
    protected long acceptedRequestsNumber;
    @XmlElement(name = "Rejected_requests_number")
    @XmlSchemaType(name = "unsignedInt")
    protected long rejectedRequestsNumber;

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the submittedRequestsNumber property.
     * 
     */
    public long getSubmittedRequestsNumber() {
        return submittedRequestsNumber;
    }

    /**
     * Sets the value of the submittedRequestsNumber property.
     * 
     */
    public void setSubmittedRequestsNumber(long value) {
        this.submittedRequestsNumber = value;
    }

    /**
     * Gets the value of the acceptedRequestsNumber property.
     * 
     */
    public long getAcceptedRequestsNumber() {
        return acceptedRequestsNumber;
    }

    /**
     * Sets the value of the acceptedRequestsNumber property.
     * 
     */
    public void setAcceptedRequestsNumber(long value) {
        this.acceptedRequestsNumber = value;
    }

    /**
     * Gets the value of the rejectedRequestsNumber property.
     * 
     */
    public long getRejectedRequestsNumber() {
        return rejectedRequestsNumber;
    }

    /**
     * Sets the value of the rejectedRequestsNumber property.
     * 
     */
    public void setRejectedRequestsNumber(long value) {
        this.rejectedRequestsNumber = value;
    }

}
