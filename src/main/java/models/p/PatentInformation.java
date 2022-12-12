//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 06:28:20 PM CET 
//


package models.p;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Patent_information complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Patent_information">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognition}Patent_name"/>
 *         &lt;element name="Applicant">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.XMLproject.ftn.uns.ac.rs/User}TPerson">
 *                 &lt;sequence>
 *                   &lt;element name="Fax_number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="applicant_is_inventor" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Inventor">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.XMLproject.ftn.uns.ac.rs/User}TIndividual">
 *                 &lt;sequence>
 *                   &lt;element name="Fax_number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="does_not_want_to_be_listed" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Patent_information", propOrder = {
    "patentName",
    "applicant",
    "inventor"
})
public class PatentInformation {

    @XmlElement(name = "Patent_name", required = true)
    protected PatentName patentName;
    @XmlElement(name = "Applicant", required = true)
    protected PatentInformation.Applicant applicant;
    @XmlElement(name = "Inventor", required = true)
    protected PatentInformation.Inventor inventor;

    /**
     * Gets the value of the patentName property.
     * 
     * @return
     *     possible object is
     *     {@link PatentName }
     *     
     */
    public PatentName getPatentName() {
        return patentName;
    }

    /**
     * Sets the value of the patentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatentName }
     *     
     */
    public void setPatentName(PatentName value) {
        this.patentName = value;
    }

    /**
     * Gets the value of the applicant property.
     * 
     * @return
     *     possible object is
     *     {@link PatentInformation.Applicant }
     *     
     */
    public PatentInformation.Applicant getApplicant() {
        return applicant;
    }

    /**
     * Sets the value of the applicant property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatentInformation.Applicant }
     *     
     */
    public void setApplicant(PatentInformation.Applicant value) {
        this.applicant = value;
    }

    /**
     * Gets the value of the inventor property.
     * 
     * @return
     *     possible object is
     *     {@link PatentInformation.Inventor }
     *     
     */
    public PatentInformation.Inventor getInventor() {
        return inventor;
    }

    /**
     * Sets the value of the inventor property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatentInformation.Inventor }
     *     
     */
    public void setInventor(PatentInformation.Inventor value) {
        this.inventor = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.XMLproject.ftn.uns.ac.rs/User}TPerson">
     *       &lt;sequence>
     *         &lt;element name="Fax_number" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="applicant_is_inventor" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "faxNumber"
    })
    public static class Applicant
        extends TPerson
    {

        @XmlElement(name = "Fax_number", required = true)
        protected String faxNumber;
        @XmlAttribute(name = "applicant_is_inventor")
        @XmlSchemaType(name = "anySimpleType")
        protected String applicantIsInventor;

        /**
         * Gets the value of the faxNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFaxNumber() {
            return faxNumber;
        }

        /**
         * Sets the value of the faxNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFaxNumber(String value) {
            this.faxNumber = value;
        }

        /**
         * Gets the value of the applicantIsInventor property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getApplicantIsInventor() {
            return applicantIsInventor;
        }

        /**
         * Sets the value of the applicantIsInventor property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setApplicantIsInventor(String value) {
            this.applicantIsInventor = value;
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
     *     &lt;extension base="{http://www.XMLproject.ftn.uns.ac.rs/User}TIndividual">
     *       &lt;sequence>
     *         &lt;element name="Fax_number" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *       &lt;attribute name="does_not_want_to_be_listed" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "faxNumber"
    })
    public static class Inventor
        extends TIndividual
    {

        @XmlElement(name = "Fax_number", required = true)
        protected String faxNumber;
        @XmlAttribute(name = "does_not_want_to_be_listed")
        @XmlSchemaType(name = "anySimpleType")
        protected String doesNotWantToBeListed;

        /**
         * Gets the value of the faxNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFaxNumber() {
            return faxNumber;
        }

        /**
         * Sets the value of the faxNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFaxNumber(String value) {
            this.faxNumber = value;
        }

        /**
         * Gets the value of the doesNotWantToBeListed property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDoesNotWantToBeListed() {
            return doesNotWantToBeListed;
        }

        /**
         * Sets the value of the doesNotWantToBeListed property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDoesNotWantToBeListed(String value) {
            this.doesNotWantToBeListed = value;
        }

    }

}
