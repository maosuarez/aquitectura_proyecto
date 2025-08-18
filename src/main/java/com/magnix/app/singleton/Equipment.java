package com.magnix.app.singleton;

import java.util.UUID;

public class Equipment {
    private final String name;
    private final String id;

    // Constructor que recibe solo el nombre y genera un ID único
    public Equipment(String name) {
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

