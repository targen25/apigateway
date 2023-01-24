package com.pcm.pronsase.apigateway.service;

import com.pcm.pronsase.apigateway.model.Role;
import com.pcm.pronsase.apigateway.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    void deleteUser(Long userId);

    List<User> findAllUser();

    @Transactional
    void changeRole(Role newRole, String username);

    User findByUsernameReturnToken(String username);
}
