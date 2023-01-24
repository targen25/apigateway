package com.pcm.pronsase.apigateway.service;

import com.pcm.pronsase.apigateway.model.User;

public interface AuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
