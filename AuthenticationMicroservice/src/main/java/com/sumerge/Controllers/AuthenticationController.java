package com.sumerge.Controllers;

import com.sumerge.Models.CredentialsRequest;
import com.sumerge.Models.JWTResponse;
import com.sumerge.Models.User;
import com.sumerge.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void signUp(@RequestBody User user)
    {
        boolean success = authenticationService.signUp(user);
        System.out.println("Sign up is: " + success);
    }
    @GetMapping(value = "/users")
    public String getUsers()
    {
        return authenticationService.getAllUsers();
    }

}
