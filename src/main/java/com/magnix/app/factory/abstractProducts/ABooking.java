package com.magnix.app.factory.abstractProducts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.magnix.app.factory.Team;
import com.magnix.app.singleton.Equipment;
import com.magnix.app.singleton.ListSports;
import com.magnix.app.factory.dependencies.Court;
import com.magnix.app.observer.ObservableOwn;
import com.magnix.app.observer.State;

// Abstracción
public abstract class ABooking extends ObservableOwn{
    private Date day;
    private int hour;
    private List<Equipment> equipment;
    private int durationMinutes;
    private Court court;
    private List<Team> teams;
    
    protected ListSports listSports;

    public abstract void setEquipment();
    public abstract void setDuration();
    public abstract void setCourt();

    protected void setDurationMinutesInternal(int minutes) { this.durationMinutes = minutes; }
    protected void setCourtInternal(Court court) { this.court = court; }
    protected void setEquipmentInternal(List<Equipment> equipment) { this.equipment = new ArrayList<>(equipment); }

    public ABooking() {
        teams = new ArrayList<>();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField dateField = new JTextField(10); // formato: dd/MM/yyyy
        JTextField hourField = new JTextField(5);  // formato: entero (0–23)

        panel.add(new JLabel("Fecha (dd/MM/yyyy):"));
        panel.add(dateField);
        panel.add(new JLabel("Hora (0–23):"));
        panel.add(hourField);

        int result = JOptionPane.showConfirmDialog(null, panel, 
            "Formulario de Reserva", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                // Parsear fecha
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date = sdf.parse(dateField.getText());
                setDay(date);

                // Parsear hora
                int hour = Integer.parseInt(hourField.getText());
                setHour(hour);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en los datos ingresados: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Reserva cancelada.");
        }

        setCourt();
        setDuration();
        setEquipment();
        
         String input = JOptionPane.showInputDialog(null,
        "📋 ¿Cuántos equipos deseas agregar?",
        "Agregar equipos",
        JOptionPane.QUESTION_MESSAGE);

        int cantidad;
        try {
            cantidad = Integer.parseInt(input);
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null,
                    "⚠️ La cantidad debe ser mayor que cero.",
                    "Entrada inválida",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "❌ Entrada no válida. Debes ingresar un número.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 1; i <= cantidad; i++) {
            JOptionPane.showMessageDialog(null,
                "🎯 Creando equipo #" + i,
                "Progreso",
                JOptionPane.INFORMATION_MESSAGE);
            addTeam();          // Método que ya tienes definido
        }

        JOptionPane.showMessageDialog(null,
            "✅ Se han agregado " + cantidad + " equipos exitosamente.",
            "Finalizado",
            JOptionPane.INFORMATION_MESSAGE);
        setState(State.CONFIRMED);

    }

    public void seeBooking() {
        StringBuilder mensaje = new StringBuilder();

        mensaje.append("📅 Fecha: ").append(day != null ? day.toString() : "No asignada").append("\n");
        mensaje.append("🕒 Hora: ").append(hour).append(":00\n");
        mensaje.append("⏱️ Duración: ").append(durationMinutes).append(" minutos\n");
        mensaje.append("🏟️ Cancha: ").append(court != null ? court.getName() : "No asignada").append("\n");

        mensaje.append("🧰 Equipamiento:\n");
        if (getEquipment() != null && !getEquipment().isEmpty()) {
            for (Equipment eq : getEquipment()) {
                mensaje.append("- ").append(eq.getName()).append("\n");
            }
        } else {
            mensaje.append("- No hay equipamiento asignado\n");
        }

        mensaje.append("🧑‍🤝‍🧑 Equipos:\n");
        if (teams != null && !teams.isEmpty()) {
            for (Team team : teams) {
                mensaje.append("- ").append(team.getName()).append("\n");
            }
        } else {
            mensaje.append("- No hay equipos asignados\n");
        }

        JOptionPane.showMessageDialog(null, mensaje.toString(), "Detalles de la Reserva", JOptionPane.INFORMATION_MESSAGE);
    }

    public  void setDay(Date day){
        this.day = day;
    };

    public  void setHour(int hour){
        this.hour = hour;
    };
    

    // TODO: Falta por Hacer
    public void cancelBooking(){
        setState(State.CANCELLED);
    };

    public void completedBooking(){
        setState(State.COMPLETED);
    }

    public  ABooking getBooking(){
        return this;
    };

    public List<Equipment> getEquipment(){
        return equipment;
    };

    public Court getCourt(){
        return this.court;
    };

    public void addTeam() {
        Team team = new Team();
        if (!teams.contains(team)) {
            teams.add(team);
            JOptionPane.showMessageDialog(null, 
                "✅ Se agregó el equipo: " + team.getName(), 
                "Equipo agregado", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, 
                "⚠️ El equipo ya está en la reserva: " + team.getName(), 
                "Equipo duplicado", 
                JOptionPane.WARNING_MESSAGE);
        }
        attach(team);
    }
}
