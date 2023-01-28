//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.19 at 04:25:12 PM CET 
//


package models.p;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Information_for_institution" type="{http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognition}Information_for_institution"/>
 *         &lt;element name="Patent_name">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Serbian_patent_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="English_patent_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Applicant" type="{http://www.XMLproject.ftn.uns.ac.rs/User}TPerson"/>
 *         &lt;element name="Inventor">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.XMLproject.ftn.uns.ac.rs/User}TIndividual">
 *                 &lt;attribute name="does_not_want_to_be_listed" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Proxy">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.XMLproject.ftn.uns.ac.rs/User}TIndividual">
 *                 &lt;attribute name="proxy_for_representation" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *                 &lt;attribute name="attorney_for_receiving_letters" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Delivery_address">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Address" type="{http://www.XMLproject.ftn.uns.ac.rs/User}Address"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element ref="{http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognition}Delivery_type"/>
 *         &lt;element name="Application_information" type="{http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognition}Application_information"/>
 *         &lt;element name="Priority_rights_recognition_from_earlier_applications" type="{http://www.XMLproject.ftn.uns.ac.rs/Request_for_patent_recognition}Priority_rights_recognition_from_earlier_applications"/>
 *       &lt;/sequence>
 *       &lt;attribute name="address" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="institution" type="{http://www.w3.org/2001/XMLSchema}string" />
 *        *       &lt;attribute name="is_accepted" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "informationForInstitution",
    "patentName",
    "applicant",
    "inventor",
    "proxy",
    "deliveryAddress",
    "deliveryType",
    "applicationInformation",
    "priorityRightsRecognitionFromEarlierApplications"
})
@XmlRootElement(name = "Request_for_patent_recognition")
public class RequestForPatentRecognition {

    @XmlElement(name = "Information_for_institution", required = true)
    protected InformationForInstitution informationForInstitution;
    @XmlElement(name = "Patent_name", required = true)
    protected RequestForPatentRecognition.PatentName patentName;
    @XmlElement(name = "Applicant", required = true)
    protected TPerson applicant;
    @XmlElement(name = "Inventor", required = true)
    protected RequestForPatentRecognition.Inventor inventor;
    @XmlElement(name = "Proxy", required = true)
    protected RequestForPatentRecognition.Proxy proxy;
    @XmlElement(name = "Delivery_address", required = true)
    protected RequestForPatentRecognition.DeliveryAddress deliveryAddress;
    @XmlElement(name = "Delivery_type", required = true)
    protected DeliveryType deliveryType;
    @XmlElement(name = "Application_information", required = true)
    protected ApplicationInformation applicationInformation;
    @XmlElement(name = "Priority_rights_recognition_from_earlier_applications", required = true)
    protected PriorityRightsRecognitionFromEarlierApplications priorityRightsRecognitionFromEarlierApplications;
    @XmlAttribute(name = "address")
    protected String address;
    @XmlAttribute(name = "institution")
    protected String institution;
    @XmlAttribute(name = "is_accepted")
    protected  Boolean isAccepted;

    /**
     * Gets the value of the informationForInstitution property.
     * 
     * @return
     *     possible object is
     *     {@link InformationForInstitution }
     *     
     */
    public InformationForInstitution getInformationForInstitution() {
        return informationForInstitution;
    }

    /**
     * Sets the value of the informationForInstitution property.
     * 
     * @param value
     *     allowed object is
     *     {@link InformationForInstitution }
     *     
     */
    public void setInformationForInstitution(InformationForInstitution value) {
        this.informationForInstitution = value;
    }

    /**
     * Gets the value of the patentName property.
     * 
     * @return
     *     possible object is
     *     {@link RequestForPatentRecognition.PatentName }
     *     
     */
    public RequestForPatentRecognition.PatentName getPatentName() {
        return patentName;
    }

    /**
     * Sets the value of the patentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestForPatentRecognition.PatentName }
     *     
     */
    public void setPatentName(RequestForPatentRecognition.PatentName value) {
        this.patentName = value;
    }

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
     * Gets the value of the inventor property.
     * 
     * @return
     *     possible object is
     *     {@link RequestForPatentRecognition.Inventor }
     *     
     */
    public RequestForPatentRecognition.Inventor getInventor() {
        return inventor;
    }

    /**
     * Sets the value of the inventor property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestForPatentRecognition.Inventor }
     *     
     */
    public void setInventor(RequestForPatentRecognition.Inventor value) {
        this.inventor = value;
    }

    /**
     * Gets the value of the proxy property.
     * 
     * @return
     *     possible object is
     *     {@link RequestForPatentRecognition.Proxy }
     *     
     */
    public RequestForPatentRecognition.Proxy getProxy() {
        return proxy;
    }

    /**
     * Sets the value of the proxy property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestForPatentRecognition.Proxy }
     *     
     */
    public void setProxy(RequestForPatentRecognition.Proxy value) {
        this.proxy = value;
    }

    /**
     * Gets the value of the deliveryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link RequestForPatentRecognition.DeliveryAddress }
     *     
     */
    public RequestForPatentRecognition.DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * Sets the value of the deliveryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestForPatentRecognition.DeliveryAddress }
     *     
     */
    public void setDeliveryAddress(RequestForPatentRecognition.DeliveryAddress value) {
        this.deliveryAddress = value;
    }

    /**
     * Gets the value of the deliveryType property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryType }
     *     
     */
    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    /**
     * Sets the value of the deliveryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryType }
     *     
     */
    public void setDeliveryType(DeliveryType value) {
        this.deliveryType = value;
    }

    /**
     * Gets the value of the applicationInformation property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationInformation }
     *     
     */
    public ApplicationInformation getApplicationInformation() {
        return applicationInformation;
    }

    /**
     * Sets the value of the applicationInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationInformation }
     *     
     */
    public void setApplicationInformation(ApplicationInformation value) {
        this.applicationInformation = value;
    }

    /**
     * Gets the value of the priorityRightsRecognitionFromEarlierApplications property.
     * 
     * @return
     *     possible object is
     *     {@link PriorityRightsRecognitionFromEarlierApplications }
     *     
     */
    public PriorityRightsRecognitionFromEarlierApplications getPriorityRightsRecognitionFromEarlierApplications() {
        return priorityRightsRecognitionFromEarlierApplications;
    }

    /**
     * Sets the value of the priorityRightsRecognitionFromEarlierApplications property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriorityRightsRecognitionFromEarlierApplications }
     *     
     */
    public void setPriorityRightsRecognitionFromEarlierApplications(PriorityRightsRecognitionFromEarlierApplications value) {
        this.priorityRightsRecognitionFromEarlierApplications = value;
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
     * Gets the value of the is_accepted property.
     *
     * @return
     *     possible object is
     *     {@link Boolean }
     *
     */
    public Boolean getIsAccepted() {
        return isAccepted;
    }

    /**
     * Sets the value of the is_accepted property.
     *
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *
     */
    public void setIsAccepted(Boolean value) {
        this.isAccepted = value;
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
     *         &lt;element name="Address" type="{http://www.XMLproject.ftn.uns.ac.rs/User}Address"/>
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
        "address"
    })
    public static class DeliveryAddress {

        @XmlElement(name = "Address", required = true)
        protected Address address;

        /**
         * Gets the value of the address property.
         * 
         * @return
         *     possible object is
         *     {@link Address }
         *     
         */
        public Address getAddress() {
            return address;
        }

        /**
         * Sets the value of the address property.
         * 
         * @param value
         *     allowed object is
         *     {@link Address }
         *     
         */
        public void setAddress(Address value) {
            this.address = value;
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
     *       &lt;attribute name="does_not_want_to_be_listed" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Inventor
        extends TIndividual
    {

        @XmlAttribute(name = "does_not_want_to_be_listed")
        @XmlSchemaType(name = "anySimpleType")
        protected Boolean doesNotWantToBeListed;

        /**
         * Gets the value of the doesNotWantToBeListed property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean getDoesNotWantToBeListed() {
            return doesNotWantToBeListed;
        }

        /**
         * Sets the value of the doesNotWantToBeListed property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setDoesNotWantToBeListed(Boolean value) {
            this.doesNotWantToBeListed = value;
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
     *         &lt;element name="Serbian_patent_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="English_patent_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "serbianPatentName",
        "englishPatentName"
    })
    public static class PatentName {

        @XmlElement(name = "Serbian_patent_name", required = true)
        protected String serbianPatentName;
        @XmlElement(name = "English_patent_name", required = true)
        protected String englishPatentName;

        /**
         * Gets the value of the serbianPatentName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSerbianPatentName() {
            return serbianPatentName;
        }

        /**
         * Sets the value of the serbianPatentName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSerbianPatentName(String value) {
            this.serbianPatentName = value;
        }

        /**
         * Gets the value of the englishPatentName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEnglishPatentName() {
            return englishPatentName;
        }

        /**
         * Sets the value of the englishPatentName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEnglishPatentName(String value) {
            this.englishPatentName = value;
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
     *       &lt;attribute name="proxy_for_representation" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *       &lt;attribute name="attorney_for_receiving_letters" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Proxy
        extends TIndividual
    {

        @XmlAttribute(name = "proxy_for_representation")
        @XmlSchemaType(name = "anySimpleType")
        protected Boolean proxyForRepresentation;
        @XmlAttribute(name = "attorney_for_receiving_letters")
        @XmlSchemaType(name = "anySimpleType")
        protected Boolean attorneyForReceivingLetters;

        /**
         * Gets the value of the proxyForRepresentation property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean getProxyForRepresentation() {
            return proxyForRepresentation;
        }

        /**
         * Sets the value of the proxyForRepresentation property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setProxyForRepresentation(Boolean value) {
            this.proxyForRepresentation = value;
        }

        /**
         * Gets the value of the attorneyForReceivingLetters property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean getAttorneyForReceivingLetters() {
            return attorneyForReceivingLetters;
        }

        /**
         * Sets the value of the attorneyForReceivingLetters property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setAttorneyForReceivingLetters(Boolean value) {
            this.attorneyForReceivingLetters = value;
        }

    }

}
