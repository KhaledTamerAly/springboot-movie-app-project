package com.sumerge.Auth.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sumerge.Auth.Services.JwtBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class JwtUtils
{
    @Autowired
    JwtBlacklistService jwtBlacklistService;
    public String generateToken(String email)
    {
       return JWT.create()
               .withSubject(email)
               .withIssuedAt(new Date(System.currentTimeMillis()))
               .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
               .sign(Algorithm.HMAC256("secret"));
    }
    public boolean validateToken(String token, String email)
    {
        DecodedJWT jwt = JWT.decode(token);
        String tokenEmail = jwt.getSubject();
        return (tokenEmail.equals(email) && jwt.getExpiresAt().after(new Date())) && !jwtBlacklistService.isBlacklisted(token);
    }
    public ArrayList<String> invalidateToken(String token)
    {
        jwtBlacklistService.addToBlacklist(token);
        return jwtBlacklistService.getBlacklistedTokens();
    }
    public String getEmail(String token)
    {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    }
}
