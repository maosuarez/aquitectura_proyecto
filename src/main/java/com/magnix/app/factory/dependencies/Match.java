package com.magnix.app.factory.dependencies;

import java.time.LocalDateTime;
import com.magnix.app.factory.Team;

public class Match {

    // Atributos
    private Team player1;
    private Team player2;
    private LocalDateTime scheduledTime;
    private MatchResult result;

    // Constructor
    public Match(Team player1, Team player2, LocalDateTime scheduledTime) {
        this.player1 = player1;
        this.player2 = player2;
        this.scheduledTime = scheduledTime;
        this.result = null; 
    }

    // Método para asignar un resultado nuevo
    public void newResult(MatchResult res) {
        this.result = res;
        System.out.println("New result registered: " + res);
    }

    // Getters y Setters
    public Team getPlayer1() {
        return player1;
    }

    public Team getPlayer2() {
        return player2;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public MatchResult getResult() {
        return result;
    }
}
