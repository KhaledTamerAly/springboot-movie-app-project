package com.sumerge.Auth.Services;

import com.sumerge.Auth.Models.User;
import com.sumerge.Auth.Repositories.UserRepository;
import com.sumerge.Auth.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

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
        User existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword()))
        {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return this.jwtUtils.generateToken(user.getEmail());
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
        boolean isExist = userRepository.existsByEmail(user.getEmail());
        if(isExist)
            return false;
        else
        {
            userRepository.save(new User(user.getName(), user.getEmail(), passwordEncoder.encode(user.getPassword())));
            return true;
        }
    }
}
