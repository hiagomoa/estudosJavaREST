package com.example.aprender.usecases;

import com.example.aprender.gateway.UserGateway;

public class DeleteUser {
    private  final UserGateway userGateway;

    public DeleteUser(UserGateway userGateway){ this.userGateway = userGateway; }

    public void execute(int id) throws Exception {

        userGateway.delete(id);

    }
}
