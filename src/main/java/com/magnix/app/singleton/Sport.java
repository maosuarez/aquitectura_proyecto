package com.magnix.app.singleton;

import java.util.ArrayList;

public class Sport {

    // 🧩 Atributos principales
    private String name;
    private int numberSpaces;
    private double cost;
    private GameFormat gameFormat;

    // 🧰 Equipamiento asociado
    private ArrayList<Equipment> equipment;

    // 🏗️ Constructor
    public Sport(String name, int numberSpaces, double cost, GameFormat gameFormat) {
        this.name = name;
        this.numberSpaces = numberSpaces;
        this.cost = cost;
        this.gameFormat = gameFormat;
        this.equipment = new ArrayList<>(); // Inicialización segura
    }

    // 📥 Getters
    public String getName() {
        return name;
    }

    public int getNumberSpaces() {
        return numberSpaces;
    }

    public double getCost() {
        return cost;
    }

    public GameFormat getGameFormat() {
        return gameFormat;
    }

    public ArrayList<Equipment> getEquipment() {
        return new ArrayList<>(equipment); // Retorno seguro (copia)
    }

    // 📤 Setters
    public void setNumberSpaces(int numberSpaces) {
        this.numberSpaces = numberSpaces;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setEquipment(ArrayList<Equipment> list) {
        if (list != null) {
            this.equipment = new ArrayList<>(list); // Copia defensiva
        }
    }

    // ➕ Métodos adicionales
    public void addEquipment(Equipment item) {
        if (item != null && !equipment.contains(item)) {
            equipment.add(item);
        }
    }

    public void removeEquipment(Equipment item) {
        equipment.remove(item);
    }

    // 🧾 Representación textual
    @Override
    public String toString() {
        return "Sport{" +
                "name='" + name + '\'' +
                ", numberSpaces=" + numberSpaces +
                ", cost=" + cost +
                ", gameFormat=" + gameFormat +
                ", equipment=" + equipment +
                '}';
    }
}
