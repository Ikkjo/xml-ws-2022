package models;

import lombok.*;

@Getter
@Setter
public class Individual extends User{

    private String name;
    private String surname;
    private String citizenship;

    private Individual(){
        super();
    }

    public Individual(String email, String password, String phoneNumber,
                      Address address, String name, String surname, String citizenship) {
        super(email, password, phoneNumber, address);
        this.name = name;
        this.surname = surname;
        this.citizenship = citizenship;
    }
}
