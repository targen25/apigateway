package com.pcm.pronsase.apigateway.service;

import com.pcm.pronsase.apigateway.model.User;
import com.pcm.pronsase.apigateway.repository.UserRepository;
import com.pcm.pronsase.apigateway.security.UserPrincipal;
import com.pcm.pronsase.apigateway.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signInAndReturnJWT(User signInRequest)
    {
/*
        User user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("El user no fue encontrado:" + signInRequest.getEmail()));
*/
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);

        User sigInUser = userPrincipal.getUser();
        sigInUser.setToken(jwt);

        return sigInUser;
    }

}
