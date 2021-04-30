package com.example.aprender.usecases;

import com.example.aprender.controller.models.UserRequest;
import com.example.aprender.gateway.UserGateway;

public class CreateUser {
    private final UserGateway userGateway;

    public CreateUser(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public UserRequest execute(UserRequest user) throws Exception {
        if (user.getName() == null){
            throw new Exception("Problemas");
        }
        userGateway.save(user);
        return user;
    }
}
