package backend.authorization.controller;

import backend.authorization.dto.LoginDTO;
import backend.authorization.dto.RegistrationDTO;
import backend.authorization.dto.SystemUserDTO;
import backend.authorization.dto.TokenDTO;
import backend.authorization.service.UserService;
import backend.authorization.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/authorization", produces= MediaType.APPLICATION_XML_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final JWTUtil jwtUtil;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            return new ResponseEntity<>(service.getTokenDto(loginDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/registration", consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<SystemUserDTO> register(@RequestBody RegistrationDTO registrationDTO) {
        try {
            return new ResponseEntity<>(service.registration(registrationDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-user")
    public ResponseEntity<SystemUserDTO> getUserFromToken(HttpServletRequest request) {
        try {
            String token = jwtUtil.getTokenFromServletRequest(request);
            return new ResponseEntity<>(service.getUserDTOFromToken(token), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
