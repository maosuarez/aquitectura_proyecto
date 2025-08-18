package com.magnix.app.factory.dependencies;

public class RuleSet {
    private String description;
    private int minPlayersPerTeam;
    private int maxPlayerPerTeam;
    private String category;

    // Constructor normal (sin JOptionPane)
    public RuleSet(String description, int minPlayersPerTeam, int maxPlayerPerTeam, String category) {
        this.description = description;
        this.minPlayersPerTeam = minPlayersPerTeam;
        this.maxPlayerPerTeam = maxPlayerPerTeam;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setNumberPlayerTeam(int min, int max) {
        this.minPlayersPerTeam = min;
        this.maxPlayerPerTeam = max;
    }

    public String getCategory() {
        return category;
    }

    // Método para mostrar los datos
    public String getSummary() {
        String info = "Descripción: " + description +
                      "\nJugadores por equipo: " + minPlayersPerTeam + " - " + maxPlayerPerTeam +
                      "\nCategoría: " + category;
        return info;
    }
}

