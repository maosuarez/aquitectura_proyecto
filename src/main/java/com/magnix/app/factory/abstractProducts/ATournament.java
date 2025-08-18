package com.magnix.app.factory.abstractProducts;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.magnix.app.factory.Team;
import com.magnix.app.factory.dependencies.Bracket;
import com.magnix.app.factory.dependencies.RuleSet;
import com.magnix.app.observer.ObservableOwn;
import com.magnix.app.observer.State;

public abstract class ATournament extends ObservableOwn{
    protected Date startDate;
    protected Date endDate;
    protected ArrayList<Team> teams;
    protected RuleSet rules;
    protected Bracket bracket;
    public boolean registrationOpen = false;

    public ATournament() {
        this.teams = new ArrayList<>();

        // 🗓️ Captura de fechas
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField startField = new JTextField(10); // dd/MM/yyyy
        JTextField endField = new JTextField(10);   // dd/MM/yyyy

        panel.add(new JLabel("Fecha de inicio (dd/MM/yyyy):"));
        panel.add(startField);
        panel.add(new JLabel("Fecha de fin (dd/MM/yyyy):"));
        panel.add(endField);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Formulario de Torneo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                startDate = sdf.parse(startField.getText());
                endDate = sdf.parse(endField.getText());

                JOptionPane.showMessageDialog(null, "📆 Fechas asignadas correctamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "❌ Error en las fechas ingresadas: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "🚫 Registro de torneo cancelado.");
            return;
        }

        // ⚙️ Configuración inicial
        setRules();
        manageBracket();

        // 👥 Equipos
        String input = JOptionPane.showInputDialog(null,
                "¿Cuántos equipos deseas registrar?",
                "Registro de Equipos", JOptionPane.QUESTION_MESSAGE);

        int cantidad;
        try {
            cantidad = Integer.parseInt(input);
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null,
                        "⚠️ La cantidad debe ser mayor que cero.",
                        "Entrada inválida", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "❌ Entrada no válida. Debes ingresar un número.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 1; i <= cantidad; i++) {
            JOptionPane.showMessageDialog(null,
                    "🎯 Registrando equipo #" + i,
                    "Progreso", JOptionPane.INFORMATION_MESSAGE);
            Team team = new Team();
            addTeam(team);
        }

        JOptionPane.showMessageDialog(null,
                "✅ Se han registrado " + cantidad + " equipos exitosamente.",
                "Finalizado", JOptionPane.INFORMATION_MESSAGE);

        openRegistration(); // Se asume que el torneo inicia con registro abierto
        setState(State.CONFIRMED);
    }

    public void cancelBooking(){
        setState(State.CANCELLED);
    };

    public void completedBooking(){
        setState(State.COMPLETED);
    }

    // Métodos abstractos: los concretos los implementan
    public abstract void closeRegistration();
    public abstract void openRegistration();
    public abstract void manageBracket();
    public abstract void setRules();

    // Métodos comunes a todos los torneos
    public void addTeam(Team team) {
        if (!teams.contains(team)) {
            teams.add(team);
            JOptionPane.showMessageDialog(null,
                    "✅ Equipo agregado: " + team.getName(),
                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "⚠️ El equipo ya está registrado: " + team.getName(),
                    "Equipo duplicado", JOptionPane.WARNING_MESSAGE);
        }
        attach(team);
        setState(State.PENDING);
    }

    public void deleteTeam(String teamName) {
        Team toRemove = null;

        for (Team t : teams) {
            if (t.getName().equalsIgnoreCase(teamName)) {
                toRemove = t;
                break;
            }
        }

        if (toRemove != null) {
            teams.remove(toRemove);
            detach(toRemove); // Si estás usando patrón Observer
            JOptionPane.showMessageDialog(null,
                "🗑️ Equipo eliminado: " + toRemove.getName(),
                "Eliminación exitosa",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                "⚠️ No se encontró ningún equipo con el nombre: " + teamName,
                "Equipo no encontrado",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public Bracket getBracket() {
        return bracket;
    }

    public RuleSet getRules() {
        return rules;
    }

    public Date getStartDate(){
        return startDate;
    }

    public Date getEndDate(){
        return endDate;
    }

    public void seeTournamentDetails() {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        sb.append("📆 Inicio: ").append(startDate != null ? sdf.format(startDate) : "No asignada").append("\n");
        sb.append("🏁 Fin: ").append(endDate != null ? sdf.format(endDate) : "No asignada").append("\n");
        sb.append("📜 Reglas: ").append(rules != null ? rules.getSummary() : "No definidas").append("\n");
        sb.append("🧩 Bracket: ").append(bracket != null ? bracket.getStructureName() : "No definido").append("\n");

        sb.append("👥 Equipos registrados:\n");
        if (!teams.isEmpty()) {
            for (Team t : teams) {
                sb.append("- ").append(t.getName()).append("\n");
            }
        } else {
            sb.append("- No hay equipos registrados\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Detalles del Torneo", JOptionPane.INFORMATION_MESSAGE);
    }
}
