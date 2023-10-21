package com.sumerge.Auth.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JwtBlacklistServiceTest {

    @InjectMocks
    JwtBlacklistService jwtBlacklistService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddToBlacklist() {
        String token = "testToken";
        jwtBlacklistService.addToBlacklist(token);

        assertTrue(jwtBlacklistService.isBlacklisted(token));
    }

    @Test
    public void testIsBlacklisted() {
        String token = "testToken";
        assertFalse(jwtBlacklistService.isBlacklisted(token));

        jwtBlacklistService.addToBlacklist(token);
        assertTrue(jwtBlacklistService.isBlacklisted(token));
    }
}
