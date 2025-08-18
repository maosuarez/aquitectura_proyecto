package com.magnix.app.factory.dependencies;

import java.util.ArrayList;

import com.magnix.app.factory.Team;

public class MatchResult {

    // Atributos
    private ArrayList<String> scores;
    private float duration;
    private Team team;

    // Constructor
    public MatchResult(ArrayList<String> scores, float duration, Team team) {
        this.scores = scores;
        this.duration = duration;
        this.team = team;
    }

    // Getters y Setters
    public ArrayList<String> getScores() {
        return scores;
    }

    public void setScores(ArrayList<String> scores) {
        this.scores = scores;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}
