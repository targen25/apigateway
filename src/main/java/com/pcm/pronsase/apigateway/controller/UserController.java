package com.pcm.pronsase.apigateway.controller;

import com.pcm.pronsase.apigateway.model.Role;
import com.pcm.pronsase.apigateway.security.UserPrincipal;
import com.pcm.pronsase.apigateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("change/{role}")
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Role role)
    {
        userService.changeRole(role, userPrincipal.getUsername());

        return ResponseEntity.ok(true);
    }

    @GetMapping("userCurrent")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        return new ResponseEntity<>(userService.findByUsernameReturnToken(userPrincipal.getUsername()), HttpStatus.OK);
    }
    @GetMapping("getUsers")
    public ResponseEntity<?> getAllUser()
    {
        return ResponseEntity.ok(userService.findAllUser());
    }
    @DeleteMapping("deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId)
    {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
