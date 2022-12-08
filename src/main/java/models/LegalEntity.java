package models;

import lombok.*;

@Getter
@Setter
public class LegalEntity extends User {

    private String businessName;

    public LegalEntity(){
        super();
    }

    public LegalEntity(String email, String password, String phoneNumber,
                       Address address, String businessName) {
        super(email, password, phoneNumber, address);
        this.businessName = businessName;
    }
}
