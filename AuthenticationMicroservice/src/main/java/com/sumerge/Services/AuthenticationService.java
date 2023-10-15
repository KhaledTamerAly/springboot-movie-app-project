package com.sumerge.Services;

import com.sumerge.Models.User;
import com.sumerge.Repositories.UserRepository;
import com.sumerge.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    private JwtUtils jwtUtils;

    public AuthenticationService()
    {
        this.jwtUtils = new JwtUtils();
    }

    public User getUserCredFromEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public String getAllUsers()
    {
        return userRepository.findAll().toString();
    }
    public String login(User user)
    {
        if(userRepository.exists(Example.of(user)))
        {
            return this.jwtUtils.generateToken(user.getName());
        }
        else
            return null;
    }
    public void logout(User user)
    {
        //new JwtUtils().invalidateToken();
    }
    public boolean signUp(User user)
    {
        boolean isExist = userRepository.existsById(user.getEmail());
        if(isExist)
            return false;
        else
        {
            userRepository.save(user);
            return true;
        }
    }
}
