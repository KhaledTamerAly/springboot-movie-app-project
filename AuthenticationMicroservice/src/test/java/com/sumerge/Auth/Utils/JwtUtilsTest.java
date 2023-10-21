package com.sumerge.Auth.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sumerge.Auth.Services.JwtBlacklistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JwtUtilsTest
{

    @InjectMocks
    JwtUtils jwtUtils;

    @Mock
    JwtBlacklistService jwtBlacklistService;

    @BeforeEach
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateToken()
    {
        String email = "test@test.com";
        String token = jwtUtils.generateToken(email);
        assertEquals(email, JWT.decode(token).getSubject());
    }

    @Test
    public void testValidateTokenSuccess()
    {
        String email = "test@test.com";
        String token = jwtUtils.generateToken(email);

        when(jwtBlacklistService.isBlacklisted(anyString())).thenReturn(false);

        assertTrue(jwtUtils.validateToken(token, email));
        verify(jwtBlacklistService, times(1)).isBlacklisted(anyString());
    }
    @Test
    public void testValidateTokenFailure()
    {
        String email = "test@test.com";
        String token = jwtUtils.generateToken(email);

        when(jwtBlacklistService.isBlacklisted(anyString())).thenReturn(true);

        assertFalse(jwtUtils.validateToken(token, email));
        verify(jwtBlacklistService, times(1)).isBlacklisted(anyString());
    }
    @Test
    public void testInvalidateToken()
    {
        String token = "testToken";
        ArrayList<String> blacklistedTokens = new ArrayList<>();
        blacklistedTokens.add(token);

        when(jwtBlacklistService.getBlacklistedTokens()).thenReturn(blacklistedTokens);
        doNothing().when(jwtBlacklistService).addToBlacklist(anyString());

        ArrayList<String> result = jwtUtils.invalidateToken(token);

        assertEquals(blacklistedTokens, result);
        verify(jwtBlacklistService, times(1)).addToBlacklist(anyString());
        verify(jwtBlacklistService, times(1)).getBlacklistedTokens();
    }
    @Test
    public void testGetEmail()
    {
        String email = "test@test.com";
        String token = jwtUtils.generateToken(email);

        assertEquals(email, jwtUtils.getEmail(token));
    }
}
