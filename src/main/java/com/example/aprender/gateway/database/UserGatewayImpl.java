package com.example.aprender.gateway.database;

import com.example.aprender.Entity.User;
import com.example.aprender.controller.models.UserRequest;
import com.example.aprender.gateway.UserGateway;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGatewayImpl implements UserGateway {

    private final JdbcTemplate jdbcTemplate;

    public UserGatewayImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(UserRequest user) {
        jdbcTemplate.update( "INSERT INTO user(name, telefone) " +
                "VALUES (?, ?);", user.getName(),  user.getTelefone());
    }

    @Override
    public User findById(int id) {
        List<User> user = jdbcTemplate.query(
                "SELECT id, name, telefone FROM user WHERE id = ?",
                (resultSet, i) -> new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("telefone")),
                id);
        return user.get(0);
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(
                "UPDATE user SET name = ?, telefone = ? WHERE id = ? ", user.getName(), user.getTelefone(), user.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
    }
}
