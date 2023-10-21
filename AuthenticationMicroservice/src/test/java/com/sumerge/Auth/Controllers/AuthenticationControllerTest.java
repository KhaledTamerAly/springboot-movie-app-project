package com.sumerge.Auth.Controllers;

import com.sumerge.Auth.Models.JWTResponse;
import com.sumerge.Auth.Models.User;
import com.sumerge.Auth.Services.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginSuccess()
    {
        User user = new User("kh", "k@gg", "123");
        String expectedToken = "testToken";
        when(authenticationService.login(user)).thenReturn(expectedToken);
        ResponseEntity<?> response = authenticationController.login(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedToken, ((JWTResponse) response.getBody()).getToken());
    }
    @Test
    public void testLoginFailure()
    {
        User user = new User("kh", "k@gg", "123");
        String expectedToken = null;
        when(authenticationService.login(user)).thenReturn(expectedToken);
        ResponseEntity<?> response = authenticationController.login(user);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSignUpSuccess() {
        User user = new User("kh", "k@gg", "123");
        when(authenticationService.signUp(user)).thenReturn(true);
        ResponseEntity<String> response = authenticationController.signUp(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User saved", response.getBody());
    }
    @Test
    public void testSignUpFailure()
    {
        User user = new User("kh", "k@gg", "123");
        when(authenticationService.signUp(user)).thenReturn(false);
        ResponseEntity<String> response =response = authenticationController.signUp(user);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User already taken", response.getBody());
    }

    @Test
    public void testSignoutFailure()
    {
        String token = "bad testToken";
        ResponseEntity<?> response = authenticationController.logout(token);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        String token2 = null;
        response = authenticationController.logout(token2);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    public void testSignoutSuccess()
    {
        String token = "Bearer testToken";
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("testToken"));
        when(authenticationService.logout(token.substring(7))).thenReturn(expected);
        ResponseEntity<?> response = authenticationController.logout(token);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testGetUsers()
    {
        String expected = "User List";
        when(authenticationService.getAllUsers()).thenReturn(expected);
        String actual = authenticationController.getUsers();
        assertEquals(expected, actual);
    }

}
