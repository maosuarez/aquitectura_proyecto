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

public class BookingTennis extends ABooking {

    public BookingTennis(){
        super();
    }

    @Override
    public void setEquipment() {
        listSports = ListSports.getInstance();
        Sport tennisSport = listSports.findByName("tennis");
        if (tennisSport != null) {
            java.util.List<Equipment> allEquipments = tennisSport.getEquipment();
            String[] optionNames = allEquipments.stream()
                .map(Equipment::getName)
                .toArray(String[]::new);

            JList<String> list = new JList<>(optionNames);
            list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            JScrollPane scrollPane = new JScrollPane(list);
            scrollPane.setPreferredSize(new Dimension(300, 150));

            int result = JOptionPane.showConfirmDialog(null,
                scrollPane,
                "Selecciona uno o más equipamientos para la cancha de tennis:",
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
            "⏱ Ingresa la duración en minutos para el partido de tennis:",
            "Duración - Tennis",
            JOptionPane.QUESTION_MESSAGE);

        int minutes;
        try {
            minutes = Integer.parseInt(input);
            if (minutes <= 0) minutes = 60;
        } catch (NumberFormatException e) {
            minutes = 60;
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
            "🏟️ Ingresa el nombre de la cancha para tennis:",
            "Cancha - Tennis",
            JOptionPane.QUESTION_MESSAGE);

        if (courtName == null || courtName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "❌ No se ingresó un nombre válido. Se asigna cancha genérica.",
                "Cancha - Tennis",
                JOptionPane.WARNING_MESSAGE);
            courtName = "Cancha Genérica Tennis";
        }

        if (!courtName.toLowerCase().contains("tennis") && !courtName.toLowerCase().contains("tenis")) {
            JOptionPane.showMessageDialog(null,
                "⚠️ Aviso: el nombre no parece de una cancha de tennis, se asigna igualmente.",
                "Cancha - Tennis",
                JOptionPane.WARNING_MESSAGE);
        }

        Court court = new Court(courtName);
        setCourtInternal(court);
    }
}