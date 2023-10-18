package com.sumerge.Auth.Services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class JwtBlacklistService {
    private ArrayList<String> blacklistedTokens;
    private ScheduledExecutorService executorService;

    public JwtBlacklistService()
    {
        this.blacklistedTokens = new ArrayList<>();
        this.executorService = Executors.newScheduledThreadPool(1);
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
        this.executorService.schedule(() -> this.blacklistedTokens.remove(token), 15, TimeUnit.MINUTES);
    }

    public boolean isBlacklisted(String token)
    {
        return this.blacklistedTokens.contains(token);
    }
}