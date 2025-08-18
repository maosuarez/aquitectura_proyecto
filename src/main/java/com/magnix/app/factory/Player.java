package com.magnix.app.factory;

public class Player {
    // Atributos privados
    private String id;
    private String email;
    private String name;

    // Constructor
    public Player(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
