package backend.patent.model.p.dto;

import javax.xml.bind.annotation.XmlAttribute;

public class InventorDTO extends TIndividualDTO {

    @XmlAttribute(name = "does_not_want_to_be_listed")
    public Boolean doesNotWantToBeListed;
}
