package com.sumerge.Auth.Services;

import com.sumerge.Auth.Models.User;
import com.sumerge.Auth.Repositories.UserRepository;
import com.sumerge.Auth.Utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {

    @InjectMocks
    AuthenticationService authenticationService;

    @Mock
    UserRepository userRepository;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtUtils jwtUtils;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserCredFromEmail()
    {
        User user = new User("t", "test@test.com", "123");
        when(userRepository.findByEmail(anyString())).thenReturn(user);
        User result = authenticationService.getUserCredFromEmail("test@test.com");
        assertEquals(user.getEmail(), result.getEmail());
        verify(userRepository, times(1)).findByEmail(anyString());
    }
    @Test
    public void testLoginSuccess() {
        User user = new User("t", "test@test.com", "123");

        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(jwtUtils.generateToken(anyString())).thenReturn("token");

        String result = authenticationService.login(user);

        assertEquals("token", result);
        verify(userRepository, times(1)).findByEmail(anyString());
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
        verify(authenticationManager, times(1)).authenticate(any());
        verify(jwtUtils, times(1)).generateToken(anyString());
    }
    @Test
    public void testLoginFailureNoUser() {
        User user = new User("t", "test@test.com", "123");

        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        String result = authenticationService.login(user);

        assertNull(result);
        verify(userRepository, times(1)).findByEmail(anyString());
    }
    @Test
    public void testLoginFailureIncorrectPassword() {
        User user = new User("t", "test@test.com", "123");

        when(userRepository.findByEmail(anyString())).thenReturn(user);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        String result = authenticationService.login(user);

        assertNull(result);
        verify(userRepository, times(1)).findByEmail(anyString());
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
    }
    @Test
    public void testLogoutSuccess() {
        ArrayList<String> invalidatedTokens = new ArrayList<>();
        invalidatedTokens.add("token");

        when(jwtUtils.invalidateToken(anyString())).thenReturn(invalidatedTokens);

        ArrayList<String> result = authenticationService.logout("token");

        assertEquals(invalidatedTokens, result);
        verify(jwtUtils, times(1)).invalidateToken(anyString());
    }
    @Test
    public void testSignUpSuccess() {
        User user = new User("t", "test@test.com", "123");

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        boolean result = authenticationService.signUp(user);

        assertTrue(result);
        verify(userRepository, times(1)).existsByEmail(anyString());
        verify(passwordEncoder, times(1)).encode(anyString());
    }
    @Test
    public void testSignUpFailure() {
        User user = new User("t", "test@test.com", "123");

        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        boolean result = authenticationService.signUp(user);

        assertFalse(result);
        verify(userRepository, times(1)).existsByEmail(anyString());
    }
}
