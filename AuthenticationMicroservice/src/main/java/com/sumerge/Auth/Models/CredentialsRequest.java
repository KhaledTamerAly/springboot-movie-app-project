package com.sumerge.Auth.Models;

public class CredentialsRequest {
    private String email;

    public CredentialsRequest(String email)
    {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
