package backend.patent.model.p.dto;

import javax.xml.bind.annotation.XmlAttribute;

public class ProxyDTO extends TIndividualDTO {

    @XmlAttribute(name = "proxy_for_representation")
    public Boolean proxyForRepresentation;
    @XmlAttribute(name = "attorney_for_receiving_letters")
    public Boolean attorneyForReceivingLetters;
}
