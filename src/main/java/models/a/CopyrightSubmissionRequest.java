//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.24 at 09:04:25 PM CET 
//


package models.a;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="Applicant" type="{http://www.XMLproject.ftn.uns.ac.rs/User}TPerson"/>
 *         &lt;element name="Attorney" type="{http://www.XMLproject.ftn.uns.ac.rs/User}TIndividual"/>
 *         &lt;element name="Author_pseudonym" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Work_title">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Main_title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Alternative_title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Adaptation_work_information" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="Original_work_title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Original_work_author" type="{http://www.XMLproject.ftn.uns.ac.rs/a-1}TAuthor"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Work_type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[A-Za-z]+"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Form_of_recording_work">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[A-z]+"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Author" type="{http://www.XMLproject.ftn.uns.ac.rs/a-1}TAuthor"/>
 *         &lt;element name="Work_made_in_business_relationship" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Way_of_using_work" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="institution" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="address" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="request_number" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="request_submission_date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "applicant",
    "attorney",
    "authorPseudonym",
    "workTitle",
    "adaptationWorkInformation",
    "workType",
    "formOfRecordingWork",
    "author",
    "workMadeInBusinessRelationship",
    "wayOfUsingWork"
})
@XmlRootElement(name = "Copyright_submission_request", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1")
public class CopyrightSubmissionRequest {

    @XmlElement(name = "Applicant", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1", required = true)
    protected TPerson applicant;
    @XmlElement(name = "Attorney", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1", required = true)
    protected TIndividual attorney;
    @XmlElement(name = "Author_pseudonym", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1")
    protected String authorPseudonym;
    @XmlElement(name = "Work_title", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1", required = true, nillable = true)
    protected CopyrightSubmissionRequest.WorkTitle workTitle;
    @XmlElement(name = "Adaptation_work_information", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1")
    protected CopyrightSubmissionRequest.AdaptationWorkInformation adaptationWorkInformation;
    @XmlElement(name = "Work_type", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1", required = true)
    protected String workType;
    @XmlElement(name = "Form_of_recording_work", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1", required = true)
    protected String formOfRecordingWork;
    @XmlElement(name = "Author", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1", required = true)
    protected TAuthor author;
    @XmlElement(name = "Work_made_in_business_relationship", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1")
    protected boolean workMadeInBusinessRelationship;
    @XmlElement(name = "Way_of_using_work", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1")
    protected String wayOfUsingWork;
    @XmlAttribute(name = "institution")
    protected String institution;
    @XmlAttribute(name = "address")
    protected String address;
    @XmlAttribute(name = "request_number", required = true)
    protected String requestNumber;
    @XmlAttribute(name = "request_submission_date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar requestSubmissionDate;

    /**
     * Gets the value of the applicant property.
     * 
     * @return
     *     possible object is
     *     {@link TPerson }
     *     
     */
    public TPerson getApplicant() {
        return applicant;
    }

    /**
     * Sets the value of the applicant property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPerson }
     *     
     */
    public void setApplicant(TPerson value) {
        this.applicant = value;
    }

    /**
     * Gets the value of the attorney property.
     * 
     * @return
     *     possible object is
     *     {@link TIndividual }
     *     
     */
    public TIndividual getAttorney() {
        return attorney;
    }

    /**
     * Sets the value of the attorney property.
     * 
     * @param value
     *     allowed object is
     *     {@link TIndividual }
     *     
     */
    public void setAttorney(TIndividual value) {
        this.attorney = value;
    }

    /**
     * Gets the value of the authorPseudonym property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorPseudonym() {
        return authorPseudonym;
    }

    /**
     * Sets the value of the authorPseudonym property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorPseudonym(String value) {
        this.authorPseudonym = value;
    }

    /**
     * Gets the value of the workTitle property.
     * 
     * @return
     *     possible object is
     *     {@link CopyrightSubmissionRequest.WorkTitle }
     *     
     */
    public CopyrightSubmissionRequest.WorkTitle getWorkTitle() {
        return workTitle;
    }

    /**
     * Sets the value of the workTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link CopyrightSubmissionRequest.WorkTitle }
     *     
     */
    public void setWorkTitle(CopyrightSubmissionRequest.WorkTitle value) {
        this.workTitle = value;
    }

    /**
     * Gets the value of the adaptationWorkInformation property.
     * 
     * @return
     *     possible object is
     *     {@link CopyrightSubmissionRequest.AdaptationWorkInformation }
     *     
     */
    public CopyrightSubmissionRequest.AdaptationWorkInformation getAdaptationWorkInformation() {
        return adaptationWorkInformation;
    }

    /**
     * Sets the value of the adaptationWorkInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CopyrightSubmissionRequest.AdaptationWorkInformation }
     *     
     */
    public void setAdaptationWorkInformation(CopyrightSubmissionRequest.AdaptationWorkInformation value) {
        this.adaptationWorkInformation = value;
    }

    /**
     * Gets the value of the workType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkType() {
        return workType;
    }

    /**
     * Sets the value of the workType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkType(String value) {
        this.workType = value;
    }

    /**
     * Gets the value of the formOfRecordingWork property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormOfRecordingWork() {
        return formOfRecordingWork;
    }

    /**
     * Sets the value of the formOfRecordingWork property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormOfRecordingWork(String value) {
        this.formOfRecordingWork = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * @return
     *     possible object is
     *     {@link TAuthor }
     *     
     */
    public TAuthor getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAuthor }
     *     
     */
    public void setAuthor(TAuthor value) {
        this.author = value;
    }

    /**
     * Gets the value of the workMadeInBusinessRelationship property.
     * 
     */
    public boolean isWorkMadeInBusinessRelationship() {
        return workMadeInBusinessRelationship;
    }

    /**
     * Sets the value of the workMadeInBusinessRelationship property.
     * 
     */
    public void setWorkMadeInBusinessRelationship(boolean value) {
        this.workMadeInBusinessRelationship = value;
    }

    /**
     * Gets the value of the wayOfUsingWork property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWayOfUsingWork() {
        return wayOfUsingWork;
    }

    /**
     * Sets the value of the wayOfUsingWork property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWayOfUsingWork(String value) {
        this.wayOfUsingWork = value;
    }

    /**
     * Gets the value of the institution property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstitution() {
        return institution;
    }

    /**
     * Sets the value of the institution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstitution(String value) {
        this.institution = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the requestNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestNumber() {
        return requestNumber;
    }

    /**
     * Sets the value of the requestNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestNumber(String value) {
        this.requestNumber = value;
    }

    /**
     * Gets the value of the requestSubmissionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestSubmissionDate() {
        return requestSubmissionDate;
    }

    /**
     * Sets the value of the requestSubmissionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestSubmissionDate(XMLGregorianCalendar value) {
        this.requestSubmissionDate = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element name="Original_work_title" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Original_work_author" type="{http://www.XMLproject.ftn.uns.ac.rs/a-1}TAuthor"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "originalWorkTitle",
        "originalWorkAuthor"
    })
    public static class AdaptationWorkInformation {

        @XmlElement(name = "Original_work_title", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1")
        protected String originalWorkTitle;
        @XmlElementRef(name = "Original_work_author", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1", type = JAXBElement.class, required = false)
        protected JAXBElement<TAuthor> originalWorkAuthor;

        /**
         * Gets the value of the originalWorkTitle property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOriginalWorkTitle() {
            return originalWorkTitle;
        }

        /**
         * Sets the value of the originalWorkTitle property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOriginalWorkTitle(String value) {
            this.originalWorkTitle = value;
        }

        /**
         * Gets the value of the originalWorkAuthor property.
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link TAuthor }{@code >}
         *     
         */
        public JAXBElement<TAuthor> getOriginalWorkAuthor() {
            return originalWorkAuthor;
        }

        /**
         * Sets the value of the originalWorkAuthor property.
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link TAuthor }{@code >}
         *     
         */
        public void setOriginalWorkAuthor(JAXBElement<TAuthor> value) {
            this.originalWorkAuthor = value;
        }

    }


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
     *         &lt;element name="Main_title" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Alternative_title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "mainTitle",
        "alternativeTitle"
    })
    public static class WorkTitle {

        @XmlElement(name = "Main_title", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1", required = true)
        protected String mainTitle;
        @XmlElement(name = "Alternative_title", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1")
        protected String alternativeTitle;

        /**
         * Gets the value of the mainTitle property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMainTitle() {
            return mainTitle;
        }

        /**
         * Sets the value of the mainTitle property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMainTitle(String value) {
            this.mainTitle = value;
        }

        /**
         * Gets the value of the alternativeTitle property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAlternativeTitle() {
            return alternativeTitle;
        }

        /**
         * Sets the value of the alternativeTitle property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAlternativeTitle(String value) {
            this.alternativeTitle = value;
        }

    }

}
