package com.example.aprender.Entity;

public class User {

    private int id;
    private String name;
    private String telefone;

    public User(){

    };

    public User(int id, String name, String telefone) {
        this.id = id;
        this.name=name;
        this.telefone=telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
