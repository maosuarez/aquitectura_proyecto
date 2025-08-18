package com.magnix.app.factory.concreteProducts;

import java.awt.Dimension;
import java.util.stream.Collectors;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.magnix.app.factory.abstractProducts.ABooking;
import com.magnix.app.factory.dependencies.Court;
import com.magnix.app.singleton.Equipment;
import com.magnix.app.singleton.ListSports;
import com.magnix.app.singleton.Sport;

public class BookingSoccer extends ABooking {

    public BookingSoccer(){
        super();
    }

     @Override
    public void setEquipment() {
        listSports = ListSports.getInstance();
        Sport soccerSport = listSports.findByName("soccer");
        if (soccerSport != null) {
            java.util.List<Equipment> allEquipments = soccerSport.getEquipment();
            String[] optionNames = allEquipments.stream()
                .map(Equipment::getName)
                .toArray(String[]::new);

            JList<String> list = new JList<>(optionNames);
            list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            JScrollPane scrollPane = new JScrollPane(list);
            scrollPane.setPreferredSize(new Dimension(300, 150));

            int result = JOptionPane.showConfirmDialog(null,
                scrollPane,
                "Selecciona uno o más equipamientos para la cancha de fútbol:",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                java.util.List<String> selectedNames = list.getSelectedValuesList();
                java.util.List<Equipment> selectedEquipments = allEquipments.stream()
                    .filter(eq -> selectedNames.contains(eq.getName()))
                    .collect(Collectors.toList());

                setEquipmentInternal(selectedEquipments);

                JOptionPane.showMessageDialog(null,
                    "✅ Equipamiento asignado: " + String.join(", ", selectedNames),
                    "Confirmación",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void setDuration() {
        String input = JOptionPane.showInputDialog(null,
            "⏱ Ingresa la duración en minutos para el partido de fútbol:",
            "Duración - Soccer",
            JOptionPane.QUESTION_MESSAGE);

        int minutes;
        try {
            minutes = Integer.parseInt(input);
            if (minutes <= 0) minutes = 90;
        } catch (NumberFormatException e) {
            minutes = 90;
        }

        JOptionPane.showMessageDialog(null,
            "✅ Duración establecida: " + minutes + " minutos.",
            "Confirmación",
            JOptionPane.INFORMATION_MESSAGE);

        setDurationMinutesInternal(minutes);
    }

    @Override
    public void setCourt() {
        String courtName = JOptionPane.showInputDialog(null,
            "🏟️ Ingresa el nombre de la cancha para fútbol:",
            "Cancha - Soccer",
            JOptionPane.QUESTION_MESSAGE);

        if (courtName == null || courtName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "❌ No se ingresó un nombre válido. Se asigna cancha genérica.",
                "Cancha - Soccer",
                JOptionPane.WARNING_MESSAGE);
            courtName = "Cancha Genérica Fútbol";
        }

        if (!courtName.toLowerCase().contains("fútbol") && !courtName.toLowerCase().contains("soccer")) {
            JOptionPane.showMessageDialog(null,
                "⚠️ Aviso: el nombre no parece de una cancha de fútbol, se asigna igualmente.",
                "Cancha - Soccer",
                JOptionPane.WARNING_MESSAGE);
        }

        Court court = new Court(courtName);
        setCourtInternal(court);
    }

}
