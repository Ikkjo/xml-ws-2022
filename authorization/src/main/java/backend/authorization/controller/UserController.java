package backend.authorization.controller;

import backend.authorization.dto.LoginDTO;
import backend.authorization.dto.RegistrationDTO;
import backend.authorization.dto.SystemUserDTO;
import backend.authorization.dto.TokenDTO;
import backend.authorization.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/authorization", produces= MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            return new ResponseEntity<>(service.getTokenDto(loginDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<SystemUserDTO> register(@RequestBody RegistrationDTO registrationDTO) {
        try {
            return new ResponseEntity<>(service.registration(registrationDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getUser/{token}")
    public ResponseEntity<SystemUserDTO> getUserFromToken(@PathVariable("token") String token) {
        try {
            return new ResponseEntity<>(service.getUserDTOFromToken(token), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
