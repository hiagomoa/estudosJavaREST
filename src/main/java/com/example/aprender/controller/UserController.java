package com.example.aprender.controller;


import com.example.aprender.Entity.User;
import com.example.aprender.controller.models.UserRequest;
import com.example.aprender.controller.models.UserResponse;
import com.example.aprender.usecases.CreateUser;
import com.example.aprender.usecases.DeleteUser;
import com.example.aprender.usecases.SearchById;
import com.example.aprender.usecases.UpdateUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final CreateUser createUser;
    private final SearchById searchById;
    private final UpdateUser updateUser;
    private final DeleteUser deleteUser;

    public UserController(CreateUser createUser, SearchById searchById, UpdateUser updateUser, DeleteUser deleteUser) {
        this.createUser = createUser;
        this.searchById = searchById;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createAccount(@RequestBody UserRequest userRequest) {
        System.out.println("PASSOU " + userRequest.getName() + " " + userRequest.getTelefone());
        try {
            createUser.execute(userRequest);
            return new ResponseEntity<>(new UserResponse(userRequest.getName(), userRequest.getTelefone()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> searchUserById(@PathVariable int id) {
        try {
            User user = searchById.execute(id);
            return new ResponseEntity<>(new User(user.getId(), user.getName(), user.getTelefone()), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody UserRequest userRequest) {
        User user = new User();
        try {
            user.setId(id);
            user.setName(userRequest.getName());
            user.setTelefone(userRequest.getTelefone());
            updateUser.execute(user);
            return new ResponseEntity<>(new User(user.getId(), user.getName(), user.getTelefone()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {

        try {
            deleteUser.execute(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
