package com.sumerge.Auth.Controllers;

import com.sumerge.Auth.Models.JWTResponse;
import com.sumerge.Auth.Models.User;
import com.sumerge.Auth.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody User user)
    {
        String token = authenticationService.login(user);
        if(token != null)
            return ResponseEntity.ok(new JWTResponse(token));
        else
            return ResponseEntity.badRequest().body("Incorrect login");
    }
    @PostMapping(value = "/signout")
    public ResponseEntity<?> logout(@RequestHeader(value="Authorization") String token) {
        if (token != null && token.startsWith("Bearer "))
        {
            token = token.substring(7);
            return ResponseEntity.ok(authenticationService.logout(token));
        }
        return new ResponseEntity<>("No token given", HttpStatus.BAD_REQUEST);
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
