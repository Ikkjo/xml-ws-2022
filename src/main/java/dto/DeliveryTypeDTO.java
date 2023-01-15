package dto;

import javax.xml.bind.annotation.XmlElement;

public class DeliveryTypeDTO {

    @XmlElement(name = "Electronic_delivery")
    public String electronicDelivery;
    @XmlElement(name = "Delivery_in_paper_form")
    public String deliveryInPaperForm;
}
