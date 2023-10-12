package com.sumerge.Services;

import com.sumerge.Models.User;
import com.sumerge.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    public String getAllUsers()
    {
        return userRepository.findAll().toString();
    }
    public boolean login(User user)
    {
        return userRepository.exists(Example.of(user));
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
