package backend.authorization.util;

import backend.authorization.dto.RegistrationDTO;
import backend.authorization.dto.SystemUserDTO;
import backend.authorization.model.SystemUser;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {

    public SystemUser convertFromDTO(RegistrationDTO registrationDTO) {
        SystemUser user = new SystemUser();

        user.setFirstName(registrationDTO.firstName);
        user.setLastName(registrationDTO.lastName);
        user.setEmail(registrationDTO.email);
        user.setPassword(registrationDTO.password);
        user.setRole(registrationDTO.role);
        return user;
    }

    public SystemUserDTO convertToDTO(SystemUser user) {
        SystemUserDTO userDTO = new SystemUserDTO();

        userDTO.firstName = user.getFirstName();
        userDTO.lastName = user.getLastName();
        userDTO.email = user.getEmail();
        userDTO.role = user.getRole();
        return userDTO;
    }
}
