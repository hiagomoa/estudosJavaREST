package com.example.aprender.gateway;

import com.example.aprender.entity.User;
import com.example.aprender.controller.models.UserRequest;

public interface UserGateway {

    void save (UserRequest user);
    User findById(int id);
    void update(User user);
    void delete(int id);


}
