package com.magnix.app.factory.dependencies;

import java.util.UUID;

public class Court {
    private final String name;
    private final String id;

    // Constructor que recibe solo el nombre y genera un ID único
    public Court(String name) {
        this.name = name;
        this.id = UUID.randomUUID().toString(); // ID aleatorio único
    }

    // Getter para name
    public String getName() {
        return name;
    }

    // Getter para id
    public String getId() {
        return id;
    }
}
