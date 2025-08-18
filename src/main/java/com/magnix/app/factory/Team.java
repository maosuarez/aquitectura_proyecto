package com.magnix.app.factory;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.magnix.app.observer.Observer;

import java.util.UUID;

public class Team implements Observer{

    private String id;
    private String name;
    private Player captain;
    private ArrayList<Player> members;

    // Constructor interactivo con ID aleatorio y entrada completa de Player
    public Team() {
        this.id = UUID.randomUUID().toString();
        this.members = new ArrayList<>();

        // Solicitar nombre del equipo
        this.name = JOptionPane.showInputDialog(null,
            "🏷️ Ingresa el nombre del equipo:",
            "Crear equipo",
            JOptionPane.QUESTION_MESSAGE);

        // Agregar miembros
        boolean agregarMas = true;
        while (agregarMas) {
            String nombreJugador = JOptionPane.showInputDialog(null,
                "👤 Ingresa el nombre del jugador:",
                "Agregar miembro",
                JOptionPane.QUESTION_MESSAGE);

            String emailJugador = JOptionPane.showInputDialog(null,
                "📧 Ingresa el email del jugador:",
                "Agregar miembro",
                JOptionPane.QUESTION_MESSAGE);

            if (nombreJugador != null && !nombreJugador.trim().isEmpty()
                && emailJugador != null && !emailJugador.trim().isEmpty()) {

                String playerId = UUID.randomUUID().toString();
                members.add(new Player(playerId, nombreJugador, emailJugador));
            }

            int respuesta = JOptionPane.showConfirmDialog(null,
                "¿Deseas agregar otro jugador?",
                "Agregar miembro",
                JOptionPane.YES_NO_OPTION);

            agregarMas = (respuesta == JOptionPane.YES_OPTION);
        }

        // Asignar capitán
        if (members.size() == 1) {
            this.captain = members.get(0);
            JOptionPane.showMessageDialog(null,
                "👑 " + captain.getName() + " ha sido asignado automáticamente como capitán.",
                "Capitán asignado",
                JOptionPane.INFORMATION_MESSAGE);
        } else if (members.size() > 1) {
            String[] nombres = members.stream()
                .map(Player::getName)
                .toArray(String[]::new);

            String seleccionado = (String) JOptionPane.showInputDialog(null,
                "Selecciona al capitán del equipo:",
                "Asignar capitán",
                JOptionPane.QUESTION_MESSAGE,
                null,
                nombres,
                nombres[0]);

            if (seleccionado != null) {
                setCaptain(seleccionado);
            }
        }

        JOptionPane.showMessageDialog(null,
            "✅ Equipo '" + name + "' creado con " + members.size() + " miembros.",
            "Equipo creado",
            JOptionPane.INFORMATION_MESSAGE);
    }

    // Métodos existentes...

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addMember(Player p) {
        members.add(p);
    }

    public void removeMember(String name) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getName().equals(name)) {
                members.remove(i);
                break;
            }
        }
    }

    public void setCaptain(String name) {
        for (Player p : members) {
            if (p.getName().equals(name)) {
                this.captain = p;
                break;
            }
        }
    }

    public void getMembers() {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Miembros del equipo ").append(name).append(":\n");

        for (Player p : members) {
            mensaje.append("- ").append(p.getName())
                   .append(" (").append(p.getEmail()).append(")\n");
        }

        if (captain != null) {
            mensaje.append("Capitán: ").append(captain.getName()).append("\n");
        }

        JOptionPane.showMessageDialog(null, mensaje.toString(), "Equipo " + name, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void update(String mensaje) {
        System.out.println("Equipo " + name + " Notificado");
        System.out.println(mensaje + "\n\n");
    }
}

