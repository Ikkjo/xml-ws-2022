package backend.authorization.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TokenDTO")
public class TokenDTO {

    @XmlElement(name = "Token")
    public String token;

    public TokenDTO(String token) {
        this.token = token;
    }
}
