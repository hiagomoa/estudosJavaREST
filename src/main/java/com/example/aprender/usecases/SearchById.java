package com.example.aprender.usecases;

import com.example.aprender.entity.User;
import com.example.aprender.gateway.UserGateway;

public class SearchById {

    private final UserGateway userGateway;

    public SearchById(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User execute(int id) throws Exception {

        User user = userGateway.findById(id);
        if(user == null){
            throw new Exception("ERRO");
        }
        return user;

    }
}
