package com.sumerge.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JwtUtils
{
    public String generateToken(String username)
    {
       return JWT.create()
               .withSubject(username)
               .withIssuedAt(new Date())
               .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
               .sign(Algorithm.HMAC256("secret"));
    }
    public boolean isValid(String token)
    {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject() != null && jwt.getExpiresAt().after(new Date());
    }
    public void invalidateToken(String token)
    {

    }
}
