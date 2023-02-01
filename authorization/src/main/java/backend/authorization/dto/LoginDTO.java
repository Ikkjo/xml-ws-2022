package backend.authorization.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LoginDTO")
public class LoginDTO {

    @XmlElement(name = "Email")
    public String email;
    @XmlElement(name = "Password")
    public String password;
}
