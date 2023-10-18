package com.sumerge.Auth.Services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtBlacklistService {
    private ArrayList<String> blacklistedTokens;

    public JwtBlacklistService()
    {
        this.blacklistedTokens = new ArrayList<>();
    }

    public ArrayList<String> getBlacklistedTokens()
    {
        return blacklistedTokens;
    }

    public void setBlacklistedTokens(ArrayList<String> blacklistedTokens)
    {
        this.blacklistedTokens = blacklistedTokens;
    }

    public void addToBlacklist(String token)
    {
        this.blacklistedTokens.add(token);
    }

    public boolean isBlacklisted(String token)
    {
        return this.blacklistedTokens.contains(token);
    }
}