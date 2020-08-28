package tracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import tracker.model.User;
import tracker.security.JwtTokenProvider;
import tracker.service.UserService;

import org.springframework.security.core.AuthenticationException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:8080")
public class SecureController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public SecureController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User requestUser) {
        try {
            String email = requestUser.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestUser.getPassword()));
            User user = userService.findUserByEmail(email);

            if (user == null) {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }

            String token = jwtTokenProvider.createToken(email, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", email);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid User registrationRequest) {
        if(registrationRequest == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        User userFromDB = userService.findByEmailOrUsername(registrationRequest.getEmail(), registrationRequest.getUsername());
        if(userFromDB != null){
            return new ResponseEntity<>("User is exists!", HttpStatus.NOT_ACCEPTABLE);
        }
        User user = new User();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(registrationRequest.getPassword());
        user.setEmail(registrationRequest.getEmail());
        userService.registerNewUser(user);
        return new ResponseEntity<>("User successfully registered!", HttpStatus.OK);
    }

}
