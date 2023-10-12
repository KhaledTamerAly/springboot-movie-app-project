package com.sumerge.Controllers;

import com.sumerge.Models.User;
import com.sumerge.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController
{
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public void login(@RequestBody User user)
    {
        authenticationService.login(user);
    }
    @PostMapping(value = "/signup")
    public void signUp(@RequestBody User user)
    {
        authenticationService.signUp(user);
    }
}
