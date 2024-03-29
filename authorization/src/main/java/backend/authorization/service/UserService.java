package backend.authorization.service;

import backend.authorization.dto.LoginDTO;
import backend.authorization.dto.RegistrationDTO;
import backend.authorization.dto.SystemUserDTO;
import backend.authorization.dto.TokenDTO;
import backend.authorization.exceptions.UserAlreadyExistException;
import backend.authorization.exceptions.UserNotFoundException;
import backend.authorization.model.SystemUser;
import backend.authorization.repository.SystemUserRepository;
import backend.authorization.util.JWTUtil;
import backend.authorization.util.UserDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final SystemUserRepository repository;
    private final UserDTOMapper dtoMapper;
    private final JWTUtil jwtUtil;

    public UserService(SystemUserRepository repository, UserDTOMapper dtoMapper, JWTUtil jwtUtil) {
        this.repository = repository;
        this.dtoMapper = dtoMapper;
        this.jwtUtil = jwtUtil;
    }

    public TokenDTO getTokenDto(LoginDTO dto) throws Exception {
        SystemUser user = login(dto);
        if (user == null)
            return null;

        return new TokenDTO(jwtUtil.generateToken(user.getEmail(), user.getRole()));
    }

    private SystemUser login(LoginDTO dto) throws Exception {
        SystemUser user = repository.findUserByEmail(dto.email);
        if (user.getPassword().equals(dto.password))
            return user;
        return null;
    }

    public SystemUserDTO registration(RegistrationDTO registrationDTO) throws Exception {
        SystemUserDTO userDto;
        try {
            repository.findUserByEmail(registrationDTO.email);
            throw new UserAlreadyExistException();
        } catch (UserNotFoundException e) {
            SystemUser user = dtoMapper.convertFromDTO(registrationDTO);
            repository.save(user);
            userDto = dtoMapper.convertToDTO(user);
        }
        return userDto;
    }

    public SystemUserDTO getUserDTOFromToken(String token) throws Exception {
        String email = jwtUtil.getEmailFromToken(token);
        SystemUser user = repository.findUserByEmail(email);
        return dtoMapper.convertToDTO(user);
    }
}
