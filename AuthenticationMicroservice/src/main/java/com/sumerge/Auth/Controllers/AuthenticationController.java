package com.sumerge.Auth.Controllers;

import com.sumerge.Auth.Models.CredentialsRequest;
import com.sumerge.Auth.Models.JWTResponse;
import com.sumerge.Auth.Models.User;
import com.sumerge.Auth.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/user")
    public ResponseEntity<?> getUserCredentials(@RequestBody CredentialsRequest credentialsRequest)
    {
        User user = authenticationService.getUserCredFromEmail(credentialsRequest.getEmail());
        return ResponseEntity.ok(user);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody User user)
    {
        String token = authenticationService.login(user);
        return ResponseEntity.ok(new JWTResponse(token));
    }
    @PostMapping(value = "/logout")
    public void logout(@RequestBody User user)
    {
        authenticationService.logout(user);
    }
    @PostMapping(value = "/signup")
    public ResponseEntity<String> signUp(@RequestBody User user)
    {
        boolean success = authenticationService.signUp(user);
        System.out.println("Sign up is: " + success);
        if(!success)
            return new ResponseEntity<>("User already taken", HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>("User saved", HttpStatus.OK);
    }
    @GetMapping(value = "/users")
    public String getUsers()
    {
        return authenticationService.getAllUsers();
    }

}
