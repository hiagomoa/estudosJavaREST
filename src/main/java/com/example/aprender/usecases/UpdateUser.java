package com.example.aprender.usecases;

import com.example.aprender.Entity.User;
import com.example.aprender.gateway.UserGateway;

public class UpdateUser {
    private final UserGateway userGateway;

    public UpdateUser(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public void execute(User user) throws Exception {

        userGateway.update(user);

    }
}
