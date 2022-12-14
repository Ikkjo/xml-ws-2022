//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 05:46:43 PM CET 
//


package models.a;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TAuthor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TAuthor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Author_surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Author_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Citizenship" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Date_of_death" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *       &lt;attribute name="anonymous" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="is_alive" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAuthor", namespace = "http://www.XMLproject.ftn.uns.ac.rs/a-1", propOrder = {
    "authorSurname",
    "authorName",
    "citizenship",
    "dateOfDeath"
})
public class TAuthor {

    @XmlElement(name = "Author_surname", required = true)
    protected String authorSurname;
    @XmlElement(name = "Author_name", required = true)
    protected String authorName;
    @XmlElement(name = "Citizenship", required = true)
    protected String citizenship;
    @XmlElement(name = "Date_of_death", required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOfDeath;
    @XmlAttribute(name = "anonymous")
    protected Boolean anonymous;
    @XmlAttribute(name = "is_alive")
    protected Boolean isAlive;

    /**
     * Gets the value of the authorSurname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorSurname() {
        return authorSurname;
    }

    /**
     * Sets the value of the authorSurname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorSurname(String value) {
        this.authorSurname = value;
    }

    /**
     * Gets the value of the authorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Sets the value of the authorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorName(String value) {
        this.authorName = value;
    }

    /**
     * Gets the value of the citizenship property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCitizenship() {
        return citizenship;
    }

    /**
     * Sets the value of the citizenship property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCitizenship(String value) {
        this.citizenship = value;
    }

    /**
     * Gets the value of the dateOfDeath property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfDeath() {
        return dateOfDeath;
    }

    /**
     * Sets the value of the dateOfDeath property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfDeath(XMLGregorianCalendar value) {
        this.dateOfDeath = value;
    }

    /**
     * Gets the value of the anonymous property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAnonymous() {
        return anonymous;
    }

    /**
     * Sets the value of the anonymous property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAnonymous(Boolean value) {
        this.anonymous = value;
    }

    /**
     * Gets the value of the isAlive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAlive() {
        return isAlive;
    }

    /**
     * Sets the value of the isAlive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAlive(Boolean value) {
        this.isAlive = value;
    }

}
