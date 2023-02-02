package backend.patent.model.p.dto;

import javax.xml.bind.annotation.XmlElement;

public class DeliveryTypeDTO {

    @XmlElement(name = "Electronic_delivery")
    public Boolean electronicDelivery;
    @XmlElement(name = "Delivery_in_paper_form")
    public Boolean deliveryInPaperForm;
}
