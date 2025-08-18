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

public class BookingBasket extends ABooking {

    public BookingBasket(){
        super();
    }

    @Override
    public void setEquipment() {
        listSports = ListSports.getInstance();
        Sport basketSport = listSports.findByName("basket");
        if (basketSport != null) {
            java.util.List<Equipment> allEquipments = basketSport.getEquipment();
            String[] optionNames = allEquipments.stream()
                .map(Equipment::getName)
                .toArray(String[]::new);

            // Crear JList con selección múltiple
            JList<String> list = new JList<>(optionNames);
            list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            JScrollPane scrollPane = new JScrollPane(list);
            scrollPane.setPreferredSize(new Dimension(300, 150));

            int result = JOptionPane.showConfirmDialog(null,
                scrollPane,
                "Selecciona uno o más equipamientos para la cancha de basket:",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                java.util.List<String> selectedNames = list.getSelectedValuesList();
                java.util.List<Equipment> selectedEquipments = allEquipments.stream()
                    .filter(eq -> selectedNames.contains(eq.getName()))
                    .collect(Collectors.toList());

                // Asignar a la variable equipment
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
            "⏱ Ingresa la duración en minutos para el partido de basket:",
            "Duración - Basket",
            JOptionPane.QUESTION_MESSAGE);

        int minutes;
        try {
            minutes = Integer.parseInt(input);
            if (minutes <= 0) minutes = 48; // Valor por defecto
        } catch (NumberFormatException e) {
            minutes = 48; // Valor por defecto si la entrada no es válida
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
            "🏟️ Ingresa el nombre de la cancha para basket:",
            "Cancha - Basket",
            JOptionPane.QUESTION_MESSAGE);

        if (courtName == null || courtName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "❌ No se ingresó un nombre válido. Se asigna cancha genérica.",
                "Cancha - Basket",
                JOptionPane.WARNING_MESSAGE);
            courtName = "Cancha Genérica Basket";
        }

        if (!courtName.toLowerCase().contains("basket")) {
            JOptionPane.showMessageDialog(null,
                "⚠️ Aviso: el nombre no parece de una cancha de basket, se asigna igualmente.",
                "Cancha - Basket",
                JOptionPane.WARNING_MESSAGE);
        }

        Court court = new Court(courtName);
        setCourtInternal(court);
    }

}
