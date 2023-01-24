package com.pcm.pronsase.apigateway.service;

import com.pcm.pronsase.apigateway.model.Role;
import com.pcm.pronsase.apigateway.model.User;
import com.pcm.pronsase.apigateway.repository.UserRepository;
import com.pcm.pronsase.apigateway.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public User saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setFechaCreacion(LocalDateTime.now());
        User userCreated = userRepository.save(user);

        String jwt = jwtProvider.generateToken(userCreated);
        userCreated.setToken(jwt);

        return userCreated;
    }

    @Override
    public Optional<User> findByUsername(String username)
    {

        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser (Long userId)
    {
        userRepository.deleteById(userId);
    }
    @Override
    public List<User> findAllUser()
    {
        return  userRepository.findAll();
    }
    @Transactional
    @Override
    public void changeRole(Role newRole, String username)
    {
        userRepository.updateUserRole(username, newRole);
    }

    @Override
    public User findByUsernameReturnToken(String username)
    {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El user no existe:" + username));

        String jwt = jwtProvider.generateToken(user);
        user.setToken(jwt);
        return user;
    }


}
