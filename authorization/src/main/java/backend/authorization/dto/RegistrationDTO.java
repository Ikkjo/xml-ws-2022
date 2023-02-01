package backend.authorization.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RegistrationDTO")
public class RegistrationDTO {

    @XmlElement(name = "First_name")
    public String firstName;
    @XmlElement(name = "Last_name")
    public String lastName;
    @XmlElement(name = "Email")
    public String email;
    @XmlElement(name = "Password")
    public String password;
    @XmlElement(name = "Role")
    public String role;
}
